package com.desafiom2y.danilo.movies.core

import com.desafiom2y.danilo.movies.data.remote.model.MovieResponse
import com.desafiom2y.danilo.movies.domain.model.Movie
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

class DTOMovieGenerate {
    companion object {
        private const val URI = "MovieResponse.json"
    }
    private var result: MovieResponse = MovieResponse()

    init {
        val moshi: Moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<MovieResponse> = moshi.adapter(MovieResponse::class.java)
        val jsonString = getStringJson(URI)
        adapter.fromJson(jsonString)?.let {
            result = it
        }
    }

    fun generateMovieModel(): Movie {
        return result.toData()
    }

}