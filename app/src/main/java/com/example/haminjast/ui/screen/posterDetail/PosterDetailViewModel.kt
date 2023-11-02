package com.example.haminjast.ui.screen.posterDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.haminjast.data.repository.PosterRepository
import com.example.haminjast.ui.model.UiPoster
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PosterDetailViewModel(posterId: Int, posterRepository: PosterRepository) : ViewModel() {

    private val _poster = MutableStateFlow<UiPoster?>(null)
    val poster = _poster.asStateFlow()

    private val _p = MutableStateFlow("null")
    val p = _p.asStateFlow()

    init {
        viewModelScope.launch {
            val result = posterRepository.getPosterById(posterId)
            result.onSuccess { poster ->
                _poster.update {
                    poster
                }
            }
        }
    }

}