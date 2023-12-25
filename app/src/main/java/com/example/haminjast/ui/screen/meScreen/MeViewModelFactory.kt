package com.example.haminjast.ui.screen.meScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.haminjast.data.datastore.LoginDataStore
import com.example.haminjast.data.network.userretrofit.UserRetrofit
import com.example.haminjast.data.network.userretrofit.UserRetrofitService
import com.example.haminjast.data.repository.UserRepository

class MeViewModelFactory(
    private val loginDataStore: LoginDataStore,
    private val userRepository: UserRepository
) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MeViewModel(userRepository, loginDataStore) as T
    }
}

fun provideUserRepository(
): UserRepository {
    return UserRepository.getInstance(
        UserRetrofit.getRetrofitInstance().create(UserRetrofitService::class.java)
    )
}

fun provideViewModelFactory(
    loginDataStore: LoginDataStore,
    userRepository: UserRepository
): ViewModelProvider.Factory {
    return MeViewModelFactory(userRepository = userRepository, loginDataStore = loginDataStore)
}