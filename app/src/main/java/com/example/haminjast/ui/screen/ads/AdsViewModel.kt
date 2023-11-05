package com.example.haminjast.ui.screen.ads

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.haminjast.data.repository.PosterRepository
import kotlinx.coroutines.flow.distinctUntilChanged

class AdsViewModel(
    posterRepository: PosterRepository
): ViewModel() {
    val posters = posterRepository.getPosters(10).flow.distinctUntilChanged().cachedIn(viewModelScope)
}