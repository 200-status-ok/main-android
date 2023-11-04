package com.example.haminjast.data

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.haminjast.data.database.RoomDB
import com.example.haminjast.data.mediator.PosterRemoteMediator
import com.example.haminjast.data.model.Poster
import com.example.haminjast.data.network.posterretrofit.PosterRetrofitService

class PosterRepository(
    private val apiService: PosterRetrofitService,
    private val db: RoomDB
) {
    @OptIn(ExperimentalPagingApi::class)
    fun getPosters(pageSize: Int): Pager<Int, Poster> {
        return Pager(
            config = PagingConfig(
                pageSize = pageSize,
                enablePlaceholders = false
            ),
            remoteMediator = PosterRemoteMediator(apiService, db),
            pagingSourceFactory = {
                db.posterDao().getAllData()
            }
        )
    }
}