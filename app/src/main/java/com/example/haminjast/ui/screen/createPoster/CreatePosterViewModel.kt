package com.example.haminjast.ui.screen.createPoster

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.haminjast.data.datastore.LoginDataStore
import com.example.haminjast.data.model.AddressAddPoster
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

    private val _imgUrls = MutableStateFlow<List<String>>(listOf())
    val imgUrls = _imgUrls.asStateFlow()

    private val _tags = MutableStateFlow<List<String>>(listOf())
    val tags = _tags.asStateFlow()

    private val _award = MutableStateFlow(0)
    val award = _award.asStateFlow()

    private val _haveChat = MutableStateFlow(false)
    val haveChat = _haveChat.asStateFlow()

    private val _address = MutableStateFlow<AddressAddPoster?>(null)
    val address = _address.asStateFlow()

    fun createPoster(){
        viewModelScope.launch(Dispatchers.IO) {
            if(loginDataStore.readTokenF() != ""){
                Log.d("dafsd", "createPoster: ${loginDataStore.readTokenF()}")
                val res = posterRepository.addPoster(
                    loginDataStore.readTokenF(),
                    address = _address.value,
                    imageUrls = _imgUrls.value,
                    title = _title.value,
                    description = _desc.value,
                    contacts = _contacts.value,
                    status = if (_posterStatus.value == PosterStatus.Lost) "lost" else "found",
                    tags = _tags.value,
                    award = _award.value,
                    haveChat = _haveChat.value
                )
                Log.d("dafsd", "createPoster: $res")
            }else{
                Log.d("dafsd", "createPoster: ${loginDataStore.readTokenF()}")
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

    fun addImageUrl(url: String) {
        _imgUrls.update {
            val ls = mutableListOf<String>()
            ls.addAll(it)
            ls.add(url)
            ls
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

    fun setHaveChat(haveChat: Boolean) {
        _haveChat.value = haveChat
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
}