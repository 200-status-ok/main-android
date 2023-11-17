package com.example.haminjast.ui.screen.myPoster

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.haminjast.data.network.posterretrofit.PosterRetrofit
import com.example.haminjast.data.network.posterretrofit.PosterRetrofitService
import com.example.haminjast.data.repository.PosterRepository

class MyPosterViewModelFactory(
    private val adsRepository: PosterRepository
) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MyPosterViewModel(adsRepository) as T
    }
}

fun provideViewModelFactory(
    context: Context,
): ViewModelProvider.Factory {
    return MyPosterViewModelFactory(providePosterRepository(context))
}
private fun providePosterRepository(
    context: Context
): PosterRepository {
    return PosterRepository(
        apiService = PosterRetrofit.getRetrofitInstance().create(PosterRetrofitService::class.java),
    )
}