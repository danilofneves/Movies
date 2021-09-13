package com.desafiom2y.danilo.movies.data.remote.service

import com.desafiom2y.danilo.movies.data.remote.model.MovieResponse
import com.desafiom2y.danilo.movies.data.remote.model.ResultGenreResponse
import com.desafiom2y.danilo.movies.data.remote.model.ResultMovieSimilarResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    companion object{
        const val MOVIE_ID = 603
    }

    @GET("movie/$MOVIE_ID")
    suspend fun getMovie(): Response<MovieResponse>

    @GET("movie/$MOVIE_ID/similar")
    suspend fun getSimilarMovies(@Query("page") page:Int): Response<ResultMovieSimilarResponse>

    @GET("genre/movie/list")
    suspend fun getAllGenres(): Response<ResultGenreResponse>
}