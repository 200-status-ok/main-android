package com.example.haminjast.ui.screen.createPoster

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.haminjast.data.datastore.LoginDataStore
import com.example.haminjast.data.network.posterretrofit.PosterRetrofit
import com.example.haminjast.data.network.posterretrofit.PosterRetrofitService
import com.example.haminjast.data.repository.LoginRepository
import com.example.haminjast.data.repository.PosterRepository

class CreatePosterViewModelFactory(
    private val loginRepository: LoginRepository,
    private val loginDataStore: LoginDataStore,
    private val posterRepository: PosterRepository
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CreatePosterViewModel(loginRepository, loginDataStore, posterRepository) as T
    }
}

private fun providePosterRepository(
): PosterRepository {
    return PosterRepository(
        apiService = PosterRetrofit.getRetrofitInstance().create(PosterRetrofitService::class.java),
    )
}

fun provideViewModelFactory(
    loginDataStore: LoginDataStore,
    loginRepository: LoginRepository
): ViewModelProvider.Factory {
    return CreatePosterViewModelFactory(loginRepository, loginDataStore, providePosterRepository())
}