package com.example.haminjast.data.mediator


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.haminjast.data.model.Poster
import com.example.haminjast.data.network.posterretrofit.PosterRetrofitService
import retrofit2.HttpException
import java.io.IOException

class PosterRemoteMediator(
    private val posterRetrofitService: PosterRetrofitService,
    private val query: String,
    private val sort: String,
    private val sortBy: String,
    private val status: String,
    private val state: String,
    private val specialType: String,
    private val lat: Double?,
    private val lon: Double?,
    private val onlyAwards: Boolean

) : PagingSource<Int, Poster>() {
    override fun getRefreshKey(state: PagingState<Int, Poster>): Int? {
        return state.anchorPosition?.let { anchorPosition->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1) ?:
            state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Poster> {
        return try {
            val currentPage = params.key ?: 1
            val posters = posterRetrofitService.getPosters(
                pageId = currentPage,
                pageSize = params.loadSize,
                sort = sort,
                sortBy = sortBy,
                status = status,
                state = state,
                specialType = specialType,
                lat = lat,
                lon = lon,
                onlyAwards = onlyAwards,
                searchPhrase = query
            )
            LoadResult.Page(
                data = posters.body()?.posters ?: emptyList(),
                prevKey = null,
                nextKey = if (posters.body()?.max_page == currentPage) null else currentPage + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

}