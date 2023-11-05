package com.example.haminjast.ui.screen.createPoster

import androidx.lifecycle.ViewModel
import com.example.haminjast.data.datastore.LoginDataStore
import com.example.haminjast.data.repository.LoginRepository
import com.example.haminjast.ui.model.Contact
import com.example.haminjast.ui.model.PosterStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CreatePosterViewModel(
    private val loginRepository: LoginRepository,
    private val loginDataStore: LoginDataStore
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

}