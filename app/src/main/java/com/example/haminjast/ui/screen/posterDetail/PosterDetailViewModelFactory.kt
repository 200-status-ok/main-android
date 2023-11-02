package com.example.haminjast.ui.screen.posterDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.haminjast.data.repository.PosterRepository

class PosterDetailViewModelFactory(
    private val posterId: Int,
    private val posterRepository: PosterRepository
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PosterDetailViewModel(posterId, posterRepository) as T
    }
}