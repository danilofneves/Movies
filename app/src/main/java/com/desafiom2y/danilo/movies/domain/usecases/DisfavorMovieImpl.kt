package com.desafiom2y.danilo.movies.domain.usecases

import com.desafiom2y.danilo.movies.domain.repository.MovieRepository
import javax.inject.Inject

class DisfavorMovieImpl @Inject constructor(
    private val repository: MovieRepository
) : DisfavorMovie {
    override operator fun invoke() = repository.disfavor()
}