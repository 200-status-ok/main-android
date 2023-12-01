package com.example.haminjast.ui.screen.chat

import com.example.haminjast.ui.model.MessageUI

data class ChatScreenState(
    val messages: List<MessageUI> = emptyList(),
    val isLoading: Boolean = true,
    val inputBarText: String = ""
)