package com.example.haminjast.ui.screen.createPoster

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.haminjast.data.datastore.LoginDataStore
import com.example.haminjast.data.repository.LoginRepository

class CreatePosterViewModelFactory(private val loginRepository: LoginRepository,
                                   private val loginDataStore: LoginDataStore
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CreatePosterViewModel(loginRepository,loginDataStore) as T
    }
}