package com.example.haminjast.ui.screen.chat

import android.media.session.MediaSession.Token
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.haminjast.User
import com.example.haminjast.data.repository.ChatRepository
import com.example.haminjast.data.repository.PosterRepository
import com.example.haminjast.ui.model.ConversationCoverUI
import com.example.haminjast.ui.model.MessageStatus
import com.example.haminjast.ui.model.MessageUI
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch

class ChatViewModel(
    private val conversationID: Long,
    private val posterID: Long,
    posterRepository: PosterRepository,
    private val chatRepository: ChatRepository,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _chatState = MutableStateFlow(ChatScreenState(conversationID = conversationID))
    val chatState = _chatState.asStateFlow()


    init {
        loadHistory()
        loadConversationCover()
    }

    private fun loadConversationCover() {
        viewModelScope.launch {
            _chatState.map { it.conversationID }.collectLatest { conversationID ->
                if (conversationID == -1L) return@collectLatest

                chatRepository.getConversationCover(conversationID).map { conversationCoverEntity ->
                    conversationCoverEntity.let { ConversationCoverUI.fromConversationCoverEntity(it) }
                }.flowOn(ioDispatcher).collectLatest { conversationCoverUI ->
                    _chatState.update { state ->
                        state.copy(
                            conversationCoverUI = conversationCoverUI
                        )
                    }
                }
            }
        }
    }

    private fun loadHistory() {

        suspend fun subscribeToMessagesDB() {
            chatRepository.getConversationHistory(conversationID).map { messageEntities ->
                Log.d("modar","")
                messageEntities.map { MessageUI.fromEntity(it) }
            }.flowOn(ioDispatcher).collectLatest { messageUIs ->
                Log.d("modar","subscribeToMessagesDB $messageUIs")
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
        val message = chatState.value.inputBarText
        updateChatState { state -> state.copy(inputBarText = "") }
        if (message.isNotEmpty()) {
            viewModelScope.launch {
                val result = chatRepository.sendMessage(
                    conversationID = conversationID,
                    posterID = posterID,
                    content = message,
                    contentType = "text" //TODO enum maybe
                )
                result.onSuccess {
                    if (it != null && conversationID == -1L) {
                        updateChatState { state -> state.copy(conversationID = conversationID) }
                    }
                }
            }
        }
    }

    fun updateChatState(update: (ChatScreenState) -> ChatScreenState) {
        _chatState.update {
            update(it)
        }
    }

    fun onMessageVisible(message: MessageUI) {
        Log.d("modarvm","onMessageVisible $message")
        viewModelScope.launch(ioDispatcher) {
            if (message.status == MessageStatus.Unread && message.senderID != User.id) {
                readMessage(listOf(message))
            }
        }
    }

    private fun readMessage(messages: List<MessageUI>) {
        Log.d("modar","readMessage $messages");
        viewModelScope.launch {
            chatRepository.readMessage(
                messages[0].senderID.toInt(),
                messages.map { it.id to it.seqNumber.toInt() },
                conversationID
            )
        }
    }

}
