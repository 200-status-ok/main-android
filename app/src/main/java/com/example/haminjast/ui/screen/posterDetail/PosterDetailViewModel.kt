package com.example.haminjast.ui.screen.posterDetail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.haminjast.data.repository.PosterRepository
import com.example.haminjast.ui.model.UiPoster
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PosterDetailViewModel(posterId: Int, posterRepository: PosterRepository) : ViewModel() {

    private val _poster = MutableStateFlow<UiPoster?>(null)
    val poster = _poster.asStateFlow()

    init {
        viewModelScope.launch {
            _poster.collectLatest {
                Log.d("modar","poster:$it");
            }
        }
        viewModelScope.launch(Dispatchers.IO) {
            val result = posterRepository.getPosterById(posterId)
            result.onSuccess { poster ->
                poster?.let {
                    val uiPoster = UiPoster.fromResponse(it)
                    _poster.update {
                        uiPoster
                    }
                }
            }
        }
    }
}