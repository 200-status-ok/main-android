package com.example.haminjast.ui.screen.chatslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.haminjast.data.datastore.LoginDataStore
import com.example.haminjast.data.network.posterretrofit.PosterRetrofit
import com.example.haminjast.data.network.posterretrofit.PosterRetrofitService
import com.example.haminjast.data.repository.ChatRepository
import com.example.haminjast.data.repository.LoginRepository
import com.example.haminjast.data.repository.PosterRepository

class ChatListViewModelFactory(
    private val chatRepository: ChatRepository,
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ChatListViewModel(chatRepository) as T
    }
}

fun provideViewModelFactory(
    chatRepository: ChatRepository
): ViewModelProvider.Factory {
    return ChatListViewModelFactory(chatRepository)
}