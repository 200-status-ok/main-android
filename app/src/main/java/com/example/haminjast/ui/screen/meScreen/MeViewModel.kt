package com.example.haminjast.ui.screen.meScreen


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.haminjast.data.datastore.LoginDataStore
import com.example.haminjast.data.repository.UserRepository
import com.example.haminjast.ui.model.UiPoster
import com.example.haminjast.ui.model.UiPoster.Companion.toUiPoster
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MeViewModel(
    private val userRepository: UserRepository,
    private val loginDataStore: LoginDataStore,
): ViewModel(){
    private val _markedPosters : MutableStateFlow<List<UiPoster>> = MutableStateFlow(listOf())
    val markedPosters = _markedPosters.asStateFlow()
    private val _wallet : MutableStateFlow<Int> = MutableStateFlow(-1)
    val wallet = _wallet.asStateFlow()
    private val _posters : MutableStateFlow<List<UiPoster>> = MutableStateFlow(listOf())
    val posters = _posters.asStateFlow()
    private val _posterId : MutableStateFlow<Int> = MutableStateFlow(-1)
    val posterId = _posterId.asStateFlow()

    private val _isMarkedPoster : MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isMarkedPoster = _isMarkedPoster.asStateFlow()

    fun markPoster(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.markPoster(loginDataStore.readTokenF(),id)
            refresh()
        }
    }

    fun unMarkPoster(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.unMarkPoster(loginDataStore.readTokenF(),id)
            refresh()
        }
    }

    fun updatePosterId(id: Int){
        _posterId.update {
            id
        }
    }

    init {
        viewModelScope.launch {
            _markedPosters.collectLatest {
                for(i in it){
                    if(i.id == _posterId.value) {
                        _isMarkedPoster.update { true }
                        return@collectLatest
                    }
                }
                _isMarkedPoster.update { false }
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            val result = userRepository.getUser(loginDataStore.readTokenF())
            if (result.isSuccess) {
                val user = result.getOrNull()
                if (user != null){
                    withContext(Dispatchers.Default){
                        _wallet.value = user.wallet
                        if(user.posters.isNullOrEmpty()){
                            _posters.value = listOf()
                        }else{
                            _posters.value = user.posters.map { it.toUiPoster() }
                        }
                        if(user.markedPosters.isNullOrEmpty()){
                            _markedPosters.value = listOf()
                        }else{
                            _markedPosters.value = user.markedPosters.map {
                                it.poster.toUiPoster()
                            }
                        }
                    }
                }
            }
        }
    }
    fun refresh(){
        viewModelScope.launch(Dispatchers.IO) {
            val result = userRepository.getUser(loginDataStore.readTokenF())
            if (result.isSuccess) {
                val user = result.getOrNull()
                if (user != null){
                    withContext(Dispatchers.Default){
                        _wallet.value = user.wallet
                        if(user.posters.isEmpty()){
                            _posters.value = listOf()
                        }else{
                            _posters.value = user.posters.map { it.toUiPoster() }
                        }
                        if(user.markedPosters.isEmpty()){
                            _markedPosters.value = listOf()
                        }else{
                            _markedPosters.value = user.markedPosters.map {
                                it.poster.toUiPoster()
                            }
                        }
                    }
                }
            }
        }
    }




}