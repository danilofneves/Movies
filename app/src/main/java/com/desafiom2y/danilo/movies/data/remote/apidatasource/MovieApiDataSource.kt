package com.desafiom2y.danilo.movies.data.remote.apidatasource

import com.desafiom2y.danilo.movies.domain.model.Genre
import com.desafiom2y.danilo.movies.domain.model.Movie
import com.desafiom2y.danilo.movies.data.Resource


interface MovieApiDataSource {

    suspend fun getMovie(): Resource<Movie>
    suspend fun getAllGenres(): Resource<List<Genre>>
}