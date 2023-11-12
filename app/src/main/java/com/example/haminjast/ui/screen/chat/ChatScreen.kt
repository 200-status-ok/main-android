@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.haminjast.ui.screen.chat

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.haminjast.ui.screen.chat.component.ChatContent
import com.example.haminjast.ui.screen.chat.component.ChatInputBar
import com.example.haminjast.ui.screen.chat.component.ChatTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    posterId: Int = 0,
    viewModel: ChatViewModel = viewModel(
        factory = provideViewModelFactory(
            posterId,
        )
    ),
    onBackClicked: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            ChatTopBar(
                onBackClicked = onBackClicked,
            )
        },
        content = {
            ChatContent(it)
        },
        bottomBar = {
            ChatInputBar()
        }
    )
}



