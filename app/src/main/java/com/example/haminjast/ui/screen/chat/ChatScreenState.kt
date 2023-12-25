package com.example.haminjast.ui.screen.chat

import com.example.haminjast.ui.model.ConversationCoverUI
import com.example.haminjast.ui.model.MessageUI

data class ChatScreenState(
    val messages: List<MessageUI> = emptyList(),
    val conversationCoverUI: ConversationCoverUI? = null,
    val isLoading: Boolean = true,
    val inputBarText: String = "",
    val conversationID:Long
)