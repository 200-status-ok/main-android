package com.example.haminjast.ui.screen.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.haminjast.data.network.posterretrofit.PosterRetrofit
import com.example.haminjast.data.network.posterretrofit.PosterRetrofitService
import com.example.haminjast.data.repository.ChatRepository
import com.example.haminjast.data.repository.PosterRepository

class ChatViewModelFactory(
    private val conversationID: Long,
    private val posterRepository: PosterRepository,
    private val chatRepository: ChatRepository,
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ChatViewModel(conversationID, posterRepository, chatRepository) as T
    }
}