package com.example.haminjast.ui.model

import com.example.haminjast.ui.screen.chat.component.MessageType

data class UiMessage(
    val id: Int,
    val text: String,
    val type: MessageType,
    val date: String,
    val time: String,
    val status: MessageStatus = MessageStatus.Pending,
)

enum class MessageStatus {
    Pending,
    Sent,
    Seen,
}