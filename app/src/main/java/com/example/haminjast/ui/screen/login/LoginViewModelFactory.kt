package com.example.haminjast.ui.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.haminjast.data.LoginRepository
import com.example.haminjast.data.datastore.LoginDataStore

class LoginViewModelFactory(private val loginRepository:LoginRepository,
    private val loginDataStore: LoginDataStore
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LoginViewModel(loginRepository,loginDataStore) as T
    }
}