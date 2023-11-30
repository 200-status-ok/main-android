package com.example.haminjast.ui.screen.chatslist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.haminjast.data.datastore.LoginDataStore
import com.example.haminjast.data.model.AddressAddPoster
import com.example.haminjast.data.model.ConversationCoverEntity
import com.example.haminjast.data.repository.ChatRepository
import com.example.haminjast.data.repository.LoginRepository
import com.example.haminjast.data.repository.PosterRepository
import com.example.haminjast.ui.component.fakeTagList
import com.example.haminjast.ui.model.Contact
import com.example.haminjast.ui.model.ConversationCoverUI
import com.example.haminjast.ui.model.PosterStatus
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChatListViewModel(
    chatRepository: ChatRepository,
    ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _conversationCovers =
        chatRepository.getConversationCovers().mapLatest { conversationCoverEntities ->
            conversationCoverEntities.map {
                ConversationCoverUI.fromConversationCoverEntityAndMessageEntity(it.key, it.value)
            }
        }.flowOn(ioDispatcher)
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val conversationCovers =
        _conversationCovers.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    init {
        viewModelScope.launch {
            chatRepository.fetchConversationCovers()
        }
    }

}