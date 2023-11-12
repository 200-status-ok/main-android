package com.example.haminjast.ui.model

data class UiConversation(
    val id: Int,
    val title: String,
    val imageUrl: String? = null,
    val lastMessage:String,
    val lastMessageDate: String,
    val unreadCount: Int,
    val myPoster:Boolean
) 