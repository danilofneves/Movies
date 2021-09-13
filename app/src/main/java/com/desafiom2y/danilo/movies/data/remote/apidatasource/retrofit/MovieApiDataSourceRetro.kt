package com.desafiom2y.danilo.movies.data.remote.apidatasource.retrofit


import com.desafiom2y.danilo.movies.data.exception.ResponseError
import com.desafiom2y.danilo.movies.data.remote.model.MovieResponse
import com.desafiom2y.danilo.movies.data.remote.model.ResultGenreResponse
import com.desafiom2y.danilo.movies.data.remote.apidatasource.MovieApiDataSource
import com.desafiom2y.danilo.movies.data.remote.helper.safeCall
import com.desafiom2y.danilo.movies.data.remote.service.MovieService
import com.desafiom2y.danilo.movies.domain.model.Genre
import com.desafiom2y.danilo.movies.domain.model.Movie
import com.desafiom2y.danilo.movies.data.Resource
import javax.inject.Inject

class MovieApiDataSourceRetro @Inject constructor(
    private val movieService: MovieService
) : MovieApiDataSource {

    override suspend fun getMovie(): Resource<Movie> {
        return when (val response = safeCall {movieService.getMovie()}) {
            is MovieResponse -> {
                Resource.Success(data = response.toData())
            }
            else -> {
                Resource.DataError(response as ResponseError)
            }
        }
    }

    override suspend fun getAllGenres(): Resource<List<Genre>> {
        return when (val response = safeCall {movieService.getAllGenres()}) {
            is ResultGenreResponse -> {
                Resource.Success(data =  response.genres.map { it.toData() })
            }
            else -> {
                Resource.DataError(response as ResponseError)
            }
        }
    }
}