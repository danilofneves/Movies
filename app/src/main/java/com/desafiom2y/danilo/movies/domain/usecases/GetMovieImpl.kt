package com.desafiom2y.danilo.movies.domain.usecases

import com.desafiom2y.danilo.movies.domain.model.Movie
import com.desafiom2y.danilo.movies.domain.repository.MovieRepository
import com.desafiom2y.danilo.movies.data.Resource
import javax.inject.Inject

class GetMovieImpl  @Inject constructor(
    private val repository: MovieRepository
) : GetMovie {
    override suspend operator fun invoke(): Resource<Movie> = repository.getMovie()
}