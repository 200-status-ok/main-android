package com.example.haminjast.ui.screen.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.haminjast.data.repository.ChatRepository
import com.example.haminjast.data.repository.PosterRepository
import com.example.haminjast.ui.model.MessageUI
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChatViewModel(
    private val conversationID: Long,
    posterRepository: PosterRepository,
    private val chatRepository: ChatRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _chatState = MutableStateFlow(ChatScreenState())
    val chatState = _chatState.asStateFlow()

    init {
        loadHistory()
    }

    private fun loadHistory() {

        suspend fun subscribeToMessagesDB() {
            chatRepository.getConversationHistory(conversationID).map { messageEntities ->
                messageEntities.map { MessageUI.fromEntity(it) }
            }.flowOn(ioDispatcher).collectLatest { messageUIs ->
                _chatState.update { state ->
                    state.copy(
                        messages = messageUIs,
                        isLoading = false
                    )
                }
            }
        }

        viewModelScope.launch {
            val response = chatRepository.fetchConversationHistory(conversationID)
            response.onSuccess {
                subscribeToMessagesDB()
            }
            response.onFailure {
                subscribeToMessagesDB()
            }
        }

    }

    fun sendMessage() {
        viewModelScope.launch {
            chatRepository.sendMessage(
                conversationID,
                chatState.value.inputBarText,
                "text"
            ) //TODO content type
        }
    }

    fun updateChatState(update: (ChatScreenState) -> ChatScreenState) {
        _chatState.update {
            update(it)
        }
    }
}