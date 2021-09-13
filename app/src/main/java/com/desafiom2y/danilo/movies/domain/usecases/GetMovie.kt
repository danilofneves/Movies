package com.desafiom2y.danilo.movies.domain.usecases

import com.desafiom2y.danilo.movies.domain.model.Movie
import com.desafiom2y.danilo.movies.data.Resource

interface GetMovie {
    suspend operator fun invoke(): Resource<Movie>
}