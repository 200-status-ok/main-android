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
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AdsViewModel(
    private val posterRepository: PosterRepository
) : ViewModel() {

    private val _screenState = MutableStateFlow(PostersScreenState())
    val screenState = _screenState.asStateFlow()

    private val _isShowingMap: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isShowingMap = _isShowingMap.asStateFlow()

    init {
        viewModelScope.launch {
            _screenState.map { it.searchPhrase }.collectLatest {
                retry()
            }
        }
    }


    var posters: Flow<PagingData<Poster>> = getPostersFlow()
    private fun getPostersFlow(): Flow<PagingData<Poster>> {
        return posterRepository.getPosters(
            pageSize = 10,
            query = _screenState.value.searchPhrase,
            sort = "desc",
            sortBy = "created_at",
            status = _screenState.value.posterFilterOptions.posterType.str,
            state = "all", //TODO this value should be "approved"
            specialType = if (_screenState.value.posterFilterOptions.onlySpecialPosters) "special" else "all",
            lat = _screenState.value.posterFilterOptions.locationOptions.lat,
            lon = _screenState.value.posterFilterOptions.locationOptions.lng,
            onlyAwards = false,
        ).flow.flowOn(Dispatchers.IO).cachedIn(viewModelScope)
    }



    fun retry() {
        Log.d("modar","retry");
        posters = getPostersFlow()
    }

    fun updateScreenState(update: (PostersScreenState) -> PostersScreenState) {
        _screenState.update { update(it) }
    }
}

data class PosterFilterOptions(
    val posterType: PosterTypeOptions = PosterTypeOptions.Both,
    val locationOptions: LocationOptions = LocationOptions(),
    val startDate: String = "شروع",
    val endDate: String = "پایان",
    val onlySpecialPosters: Boolean = false
) {
    enum class PosterTypeOptions(val str: String) {
        Both("both"),
        Found("found"),
        Lost("lost")
    }

    data class LocationOptions(
        val vicinity: String = "موقعیت مکانی",
        val lat: Double? = null,
        val lng: Double? = null
    )
}