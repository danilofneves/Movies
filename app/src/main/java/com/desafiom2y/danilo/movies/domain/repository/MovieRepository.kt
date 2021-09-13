package com.desafiom2y.danilo.movies.domain.repository

import androidx.paging.PagingData
import com.desafiom2y.danilo.movies.domain.model.Movie
import com.desafiom2y.danilo.movies.data.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovie(): Resource<Movie>
    suspend fun getSimilarMovies(): Flow<PagingData<Movie>>
    fun favorite()
    fun disfavor()
    fun isFavorite(): Boolean
}