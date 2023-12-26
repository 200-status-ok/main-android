@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.haminjast.ui.screen.chat

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.haminjast.data.database.ApplicationDataBase
import com.example.haminjast.data.network.ChatService
import com.example.haminjast.data.network.posterretrofit.PosterRetrofit
import com.example.haminjast.data.network.posterretrofit.PosterRetrofitService
import com.example.haminjast.data.repository.ChatRepository
import com.example.haminjast.data.repository.PosterRepository
import com.example.haminjast.ui.screen.chat.component.ChatContent
import com.example.haminjast.ui.screen.chat.component.ChatInputBar
import com.example.haminjast.ui.screen.chat.component.ChatTopBar

@Composable
fun ChatScreen(
    conversationID: Long,
    posterID: Long,
    viewModel: ChatViewModel = viewModel(
        factory = ChatViewModelFactory(
            conversationID = conversationID,
            posterID = posterID,
            posterRepository = PosterRepository(
                apiService = PosterRetrofit.getRetrofitInstance()
                    .create(PosterRetrofitService::class.java),
            ),
            chatRepository = ChatRepository.getInstance(
                ApplicationDataBase.getInstance(LocalContext.current).chatDao(),
                PosterRetrofit.getRetrofitInstance().create(ChatService::class.java)
            )
        )
    ),
    onBackClicked: () -> Unit = {}
) {
    val chatState by viewModel.chatState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            ChatTopBar(
                onBackClicked = onBackClicked,
                onMenuClicked = {
                    //TODO
                }
            )
        },
        content = {
            chatState.conversationCoverUI?.let { conversationCoverUi ->
                if (chatState.messages.isNotEmpty()) {
                    ChatContent(
                        it,
                        messages = chatState.messages,
                        lastReadMessageSeqNumber = conversationCoverUi.lastReadMessageSeqNumber,
                        onMessageVisible = {
                            viewModel.onMessageVisible(it)
                        }
                    )
                }
            }
        },
        bottomBar = {
            ChatInputBar(
                inputBarText = chatState.inputBarText,
                onInputBarTextChanged = { text ->
                    viewModel.updateChatState { it.copy(inputBarText = text) }
                },
                onSendClicked = { viewModel.sendMessage() }
            )
        }
    )
}



