package com.desafiom2y.danilo.movies.data.remote.apidatasource.retrofit

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.desafiom2y.danilo.movies.data.exception.ResponseError
import com.desafiom2y.danilo.movies.data.remote.model.ResultMovieSimilarResponse
import com.desafiom2y.danilo.movies.data.remote.helper.safeCall
import com.desafiom2y.danilo.movies.data.remote.service.MovieService
import com.desafiom2y.danilo.movies.domain.model.Movie
import javax.inject.Inject

class MoviePagingApiDataSourceRetro @Inject constructor(
    private val movieService: MovieService
) : PagingSource<Int,Movie>(){

    private val DEFAULT_PAGE_INDEX= 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val page = params.key ?: DEFAULT_PAGE_INDEX
        return when (val response = safeCall {
            movieService.getSimilarMovies(page)}) {
            is ResultMovieSimilarResponse -> {
                LoadResult.Page(
                    response.results.map{it.toData()},
                    prevKey = null,
                    nextKey = response.page+1
                )
            }
            else -> {
                LoadResult.Error(response as ResponseError)
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return null
    }

}