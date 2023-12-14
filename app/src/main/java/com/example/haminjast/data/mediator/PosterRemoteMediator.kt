package com.example.haminjast.data.mediator


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.haminjast.data.model.Poster
import com.example.haminjast.data.network.posterretrofit.PosterRetrofitService
import retrofit2.HttpException
import java.io.IOException

class PosterRemoteMediator(
    private val posterRetrofitService: PosterRetrofitService
) : PagingSource<Int, Poster>() {
    override fun getRefreshKey(state: PagingState<Int, Poster>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Poster> {
        return try {
            val currentPage = params.key ?: 1
            val posters = posterRetrofitService.getPosters(
                pageId = currentPage,
                pageSize = params.loadSize,
                sort = "desc",
                sortBy = "created_at",
                status = "both",
                state = "all",
                specialType = "all"
            )
            LoadResult.Page(
                data = posters.body()?.posters ?: emptyList(),
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (posters.body()?.posters?.isEmpty() == true) null else null
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

}