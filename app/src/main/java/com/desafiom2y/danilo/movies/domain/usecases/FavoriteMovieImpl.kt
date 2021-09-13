package com.desafiom2y.danilo.movies.domain.usecases

import com.desafiom2y.danilo.movies.domain.repository.MovieRepository
import javax.inject.Inject

class FavoriteMovieImpl @Inject constructor(
    private val repository: MovieRepository
) : FavoriteMovie{
    override operator fun invoke() = repository.favorite()
}