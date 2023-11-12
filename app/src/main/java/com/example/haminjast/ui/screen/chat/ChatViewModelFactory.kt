package com.example.haminjast.ui.screen.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.haminjast.data.network.posterretrofit.PosterRetrofit
import com.example.haminjast.data.network.posterretrofit.PosterRetrofitService
import com.example.haminjast.data.repository.PosterRepository

class ChatViewModelFactory(
    private val posterId: Int,
    private val posterRepository: PosterRepository
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ChatViewModel(posterId, posterRepository) as T
    }
}

fun provideViewModelFactory(
    posterId: Int,
): ViewModelProvider.Factory {
    return ChatViewModelFactory(posterId,providePosterRepository())
}
private fun providePosterRepository(
): PosterRepository {
    return PosterRepository(
        apiService = PosterRetrofit.getRetrofitInstance().create(PosterRetrofitService::class.java),
    )
}