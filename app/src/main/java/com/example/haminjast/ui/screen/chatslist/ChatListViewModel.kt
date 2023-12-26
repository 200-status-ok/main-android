package com.example.haminjast.ui.screen.chatslist

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.haminjast.User
import com.example.haminjast.WSClient
import com.example.haminjast.data.repository.ChatRepository
import com.example.haminjast.ui.model.ConversationCoverUI
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ChatListViewModel(
    private val chatRepository: ChatRepository,
    ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _conversationCovers =
        chatRepository.getConversationCovers().mapLatest { conversationCoverEntities ->
            conversationCoverEntities.map {
                Log.d(
                    "modarsn",
                    "${it.key.title} lastReadMessageSeqNumber:${it.key.lastReadMessageSeqNumber} lastMessageSeqNumber:${it.value?.seqNumber}"
                );
                ConversationCoverUI.fromConversationCoverEntityAndMessageEntity(it.key, it.value)
            }
        }.map { conversationCoverUI ->
            conversationCoverUI.forEach {
                Log.d(
                    "modarsn",
                    "${it.title} unreadCount:${it.unreadCount}"
                );
            }
            conversationCoverUI
        }.flowOn(ioDispatcher)

    val conversationCovers =
        _conversationCovers.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    init {
        viewModelScope.launch {
            _conversationCovers.collectLatest {
                Log.d("modarsn","collect $it");
            }
        }
        viewModelScope.launch {
            chatRepository.fetchConversationCovers() //TODO handle error
        }
    }

}