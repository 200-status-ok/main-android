package com.example.haminjast.ui.screen.ads

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.haminjast.data.network.posterretrofit.PosterRetrofit
import com.example.haminjast.data.network.posterretrofit.PosterRetrofitService
import com.example.haminjast.data.repository.PosterRepository

class AdsViewModelFactory(
    private val adsRepository: PosterRepository
) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AdsViewModel(adsRepository) as T
    }
}

fun provideViewModelFactory(
    context: Context,
): ViewModelProvider.Factory {
    return AdsViewModelFactory(providePosterRepository(context))
}
private fun providePosterRepository(
    context: Context
): PosterRepository {
    return PosterRepository(
        apiService = PosterRetrofit.getRetrofitInstance().create(PosterRetrofitService::class.java),
    )
}



