package com.example.haminjast.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.haminjast.data.database.RoomDB
import com.example.haminjast.data.model.Poster
import com.example.haminjast.data.network.posterretrofit.PosterRetrofitService
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PosterRemoteMediator(
    private val posterRetrofitService: PosterRetrofitService,
    private val db: RoomDB
) : RemoteMediator<Int, Poster>() {
    override suspend fun load(loadType: LoadType, state: PagingState<Int, Poster>): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        1
                    } else {
                        (lastItem.id / state.config.pageSize) + 1
                    }
                }
            }
            val pageId = loadKey ?: 1
            val pageSize = state.config.pageSize
            val response = posterRetrofitService.getPosters(
                pageId = pageId,
                pageSize = pageSize,
                sort = "desc",
                sortBy = "created_at",
                status = "both",
                state = "all",
                specialType = "all"
            )
            val posters = response.body()?.posters ?: emptyList()
            db.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    db.posterDao().deleteData()
                }
                db.posterDao().upsert(posters)
            }

            MediatorResult.Success(endOfPaginationReached = posters.isEmpty())
        } catch (e: IOException) {
            MediatorResult.Error(e)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }
}