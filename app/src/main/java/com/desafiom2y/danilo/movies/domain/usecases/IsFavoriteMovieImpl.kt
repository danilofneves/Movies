package com.desafiom2y.danilo.movies.domain.usecases

import com.desafiom2y.danilo.movies.domain.repository.MovieRepository
import javax.inject.Inject

class IsFavoriteMovieImpl @Inject constructor(
    private val repository: MovieRepository
) : IsFavoriteMovie {
    override operator fun invoke():Boolean = repository.isFavorite()
}