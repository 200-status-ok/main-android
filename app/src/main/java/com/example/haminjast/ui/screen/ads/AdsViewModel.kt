package com.example.haminjast.ui.screen.ads

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.haminjast.data.model.Poster
import com.example.haminjast.data.repository.PosterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class AdsViewModel(
    private val posterRepository: PosterRepository
): ViewModel() {

    private val _isShowingMap : MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isShowingMap = _isShowingMap.asStateFlow()

    fun onPosterSearchQueryChanged(query: String) {
        _posterSearchQuery.value = query
    }
    private val _posterSearchQuery : MutableStateFlow<String> = MutableStateFlow("")
    val posterSearchQuery = _posterSearchQuery.asStateFlow()

    fun onPosterSortByChanged(sortBy: String) {
        _posterSortBy.value = sortBy
    }
    private val _posterSortBy : MutableStateFlow<String> = MutableStateFlow("created_at")
    val posterSortBy = _posterSortBy.asStateFlow()

    fun onPosterSortOrderChanged(sortOrder: String) {
        _posterSortOrder.value = sortOrder
    }
    private val _posterSortOrder : MutableStateFlow<String> = MutableStateFlow("desc")
    val posterSortOrder = _posterSortOrder.asStateFlow()

    fun onPosterStatusChanged(status: String) {
        _posterStatus.value = status
    }
    private val _posterStatus : MutableStateFlow<String> = MutableStateFlow("both")
    val posterStatus = _posterStatus.asStateFlow()

    fun onOnlyWithAwardChanged(onlyWithAward: Boolean) {
        _onlyWithAward.value = onlyWithAward
    }
    private val _onlyWithAward : MutableStateFlow<Boolean> = MutableStateFlow(false)
    val onlyWithAward = _onlyWithAward.asStateFlow()

    fun onPosterStateChanged(state: String) {
        _posterState.value = state
    }
    private val _posterState : MutableStateFlow<String> = MutableStateFlow("all")
    val posterState = _posterState.asStateFlow()

    fun onPosterSpecialTypeChanged(specialType: String) {
        _posterSpecialType.value = specialType
    }
    private val _posterSpecialType : MutableStateFlow<String> = MutableStateFlow("all")
    val posterSpecialType = _posterSpecialType.asStateFlow()

    fun onPosterLatChanged(lat: Double?) {
        _posterLat.value = lat
    }
    private val _posterLat : MutableStateFlow<Double?> = MutableStateFlow(null)
    val posterLat = _posterLat.asStateFlow()

    fun onPosterLonChanged(lon: Double?) {
        _posterLon.value = lon
    }
    private val _posterLon : MutableStateFlow<Double?> = MutableStateFlow(null)
    val posterLon = _posterLon.asStateFlow()

    var posters : Flow<PagingData<Poster>> = getPostersFlow()
    private fun getPostersFlow() : Flow<PagingData<Poster>> {
        Log.d("AdsViewModel", "getPosters: ${_posterStatus.value}")
        return posterRepository.getPosters(
                10,
                _posterSearchQuery.value,
                _posterSortOrder.value,
                _posterSortBy.value,
                _posterStatus.value,
                _posterState.value,
                _posterSpecialType.value,
                _posterLat.value,
                _posterLon.value,
                _onlyWithAward.value,
            ).flow.flowOn(Dispatchers.IO).cachedIn(viewModelScope)
    }

    fun retry() {
        posters = getPostersFlow()
    }

    fun onToggleMapClicked() {
        _isShowingMap.value = !_isShowingMap.value
    }
}