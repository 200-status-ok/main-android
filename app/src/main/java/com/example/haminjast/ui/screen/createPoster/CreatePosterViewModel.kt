package com.example.haminjast.ui.screen.createPoster

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.haminjast.data.datastore.LoginDataStore
import com.example.haminjast.data.repository.LoginRepository
import com.example.haminjast.data.repository.PosterRepository
import com.example.haminjast.ui.component.fakeTagList
import com.example.haminjast.ui.model.Contact
import com.example.haminjast.ui.model.PosterStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream

class CreatePosterViewModel(
    private val loginRepository: LoginRepository,
    private val loginDataStore: LoginDataStore,
    private val posterRepository: PosterRepository
) : ViewModel() {

    private val _showAddContactDialog = MutableStateFlow(false)
    val showAddContactDialog = _showAddContactDialog.asStateFlow()

    private val _title = MutableStateFlow("")
    val title = _title.asStateFlow()

    private val _desc = MutableStateFlow("")
    val desc = _desc.asStateFlow()

    private val _contacts = MutableStateFlow<List<Contact>>(emptyList())
    val contacts = _contacts.asStateFlow()

    private val _posterStatus = MutableStateFlow(PosterStatus.Lost)
    val posterStatus = _posterStatus.asStateFlow()

    private val _uploadedImages = MutableStateFlow<List<UploadedImage>>(listOf())
    val uploadedImages = _uploadedImages.asStateFlow()

    private val _tags = MutableStateFlow<List<String>>(listOf())
    val tags = _tags.asStateFlow()

    private val _award = MutableStateFlow(0)
    val award = _award.asStateFlow()

    private val _latLong = MutableStateFlow(35.0 to 35.0)
    val latLong = _latLong.asStateFlow()


    fun createPoster() {
        viewModelScope.launch(Dispatchers.IO) {
            val token = loginDataStore.readTokenF()
            if (token != "") {
                posterRepository.addPoster(
                    token = token,
                    latLong = _latLong.value,
                    imageUrls = _uploadedImages.value.mapNotNull { it.uploadedUrl },
                    title = _title.value,
                    description = _desc.value,
                    contacts = _contacts.value,
                    status = if (_posterStatus.value == PosterStatus.Lost) "lost" else "found",
                    tags = _tags.value,
                    award = _award.value,
                )
            }
        }
    }


    private val _tagFieldText = MutableStateFlow<String>("")
    val tagFieldText = _tagFieldText.asStateFlow()

    private val _suggestedTags = MutableStateFlow<List<String>>(listOf())
    val suggestedTags = _suggestedTags.asStateFlow()

    private val _selectedTags = MutableStateFlow<List<String>>(listOf())
    val selectedTags = _selectedTags.asStateFlow()


    init {
        viewModelScope.launch {
            _tagFieldText.collectLatest { fieldText ->
                if (fieldText.isEmpty()) {
                    _suggestedTags.update {
                        listOf()
                    }
                    return@collectLatest
                }
                _suggestedTags.update {
                    val ls = fakeTagList.filter { it.contains(fieldText) }
                    ls
                }
            }
        }
    }


    fun setTitle(title: String) {
        _title.value = title
    }

    fun setDesc(desc: String) {
        _desc.value = desc
    }

    fun setPosterStatus(status: PosterStatus) {
        _posterStatus.value = status
    }

    fun addImageUri(uri: Uri) {
        if (!_uploadedImages.value.any { it.uri == uri }) {
            _uploadedImages.update {
                val ls = mutableListOf<UploadedImage>()
                ls.addAll(it)
                ls.add(UploadedImage(uri))
                ls
            }
        }
    }

    fun addContact(contact: Contact) {
        _contacts.update {
            val ls = mutableListOf<Contact>()
            ls.addAll(it)
            ls.add(contact)
            ls
        }
    }

    fun removeContact(contact: Contact) {
        _contacts.update {
            val ls = mutableListOf<Contact>()
            ls.addAll(it)
            ls.remove(contact)
            ls
        }
    }

    fun setShowAddContactDialog(show: Boolean) {
        _showAddContactDialog.value = show
    }

    fun setTags(tags: List<String>) {
        _tags.value = tags
    }

    fun setAward(award: Int) {
        _award.value = award
    }

    fun setTagFieldText(text: String) {
        _tagFieldText.value = text
    }

    fun addSelectedTag(tag: String) {
        _selectedTags.update {
            val ls = mutableListOf<String>()
            ls.addAll(it)
            ls.add(tag)
            ls
        }
    }

    fun removeSelectedTag(tag: String) {
        _selectedTags.update {
            val ls = mutableListOf<String>()
            ls.addAll(it)
            ls.remove(tag)
            ls
        }
    }

    fun setLatLong(lat: Double, long: Double) {
        _latLong.update {
            lat to long
        }
    }

    fun onAiSuggestionsClicked() {
        viewModelScope.launch(Dispatchers.IO) {
            uploadedImages.value.firstOrNull { it.uploadStatus == UploadStatus.UploadSucceed && !it.uploadedUrl.isNullOrEmpty() }
                ?.let {
                    val uploadedUrl = it.uploadedUrl ?: return@let
                    val res = posterRepository.generatePosterInfo(uploadedUrl)
                    res.onSuccess { generatedInfo ->
                        Log.d("modar", "success $generatedInfo");
                        generatedInfo ?: return@onSuccess
                        _title.update {
                            generatedInfo.titles.first()
                        }
                        _desc.update {
                            generatedInfo.description
                        }
                        _tags.update {
                            generatedInfo.tags
                        }
                    }
                    res.onFailure {
                        Log.d("modar", "failure");
                    }
                }
        }
    }

    fun uploadImage(context: Context, uri: Uri) {
        updateUploadedImageState(
            uri = uri,
            uploadStatus = UploadStatus.Uploading
        )

        viewModelScope.launch(Dispatchers.IO) {

            val cacheDir = context.cacheDir
            val file = File(cacheDir, "temp")

            context.contentResolver.openInputStream(uri)?.use { ins ->
                FileOutputStream(file).use { output ->
                    val buffer = ByteArray(4 * 1024)
                    var read: Int
                    while (ins.read(buffer).also { read = it } != -1) {
                        output.write(buffer, 0, read)
                    }
                    output.flush()
                }
            }

            val res = posterRepository.uploadImage(file)
            res.onSuccess { uploadImageResponse ->
                Log.d("modar", "success ${uploadImageResponse?.url}");
                if (uploadImageResponse?.url.isNullOrEmpty()) {
                    updateUploadedImageState(
                        uri = uri,
                        uploadStatus = UploadStatus.UploadFailed
                    )
                } else {
                    updateUploadedImageState(
                        uri = uri,
                        uploadedUrl = uploadImageResponse?.url,
                        uploadStatus = UploadStatus.UploadSucceed
                    )
                }
            }
            res.onFailure {
                Log.d("modar", "failure ${it.message}");
                updateUploadedImageState(
                    uri = uri,
                    uploadStatus = UploadStatus.UploadFailed
                )
            }
        }
    }

    private fun updateUploadedImageState(
        uri: Uri,
        uploadedUrl: String? = null,
        uploadStatus: UploadStatus? = null
    ) {

        _uploadedImages.update { uploadedImages ->
            val ls = mutableListOf<UploadedImage>()
            ls.addAll(uploadedImages)
            val index = ls.indexOfFirst { it.uri == uri }
            if (index != -1) {
                uploadedUrl?.let { ls[index] = ls[index].copy(uploadedUrl = uploadedUrl) }
                uploadStatus?.let {  ls[index] = ls[index].copy(uploadStatus = uploadStatus) }
            }
            ls
        }
    }

}

data class UploadedImage(
    val uri: Uri,
    val uploadedUrl: String? = null,
    val uploadStatus: UploadStatus = UploadStatus.Normal
)

enum class UploadStatus {
    Normal,
    Uploading,
    UploadFailed,
    UploadSucceed
}