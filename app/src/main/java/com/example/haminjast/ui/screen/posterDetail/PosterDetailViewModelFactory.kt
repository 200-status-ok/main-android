package com.example.haminjast.ui.screen.posterDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PosterDetailViewModelFactory(private val posterId:Int) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PosterDetailViewModel(posterId) as T
    }
}