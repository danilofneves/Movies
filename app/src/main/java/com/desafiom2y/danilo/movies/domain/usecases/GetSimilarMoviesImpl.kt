package com.desafiom2y.danilo.movies.domain.usecases

import androidx.paging.PagingData
import com.desafiom2y.danilo.movies.domain.model.Movie
import com.desafiom2y.danilo.movies.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSimilarMoviesImpl  @Inject constructor(
    private val repository: MovieRepository
) : GetSimilarMovies {
    override suspend operator fun invoke(): Flow<PagingData<Movie>> = repository.getSimilarMovies()
}