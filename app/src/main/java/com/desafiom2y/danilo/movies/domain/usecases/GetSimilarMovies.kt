package com.desafiom2y.danilo.movies.domain.usecases

import androidx.paging.PagingData
import com.desafiom2y.danilo.movies.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface GetSimilarMovies {
    suspend operator fun invoke(): Flow<PagingData<Movie>>
}