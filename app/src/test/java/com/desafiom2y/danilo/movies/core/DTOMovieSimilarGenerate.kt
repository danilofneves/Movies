package com.desafiom2y.danilo.movies.core

import com.desafiom2y.danilo.movies.data.remote.model.MovieResponse
import com.desafiom2y.danilo.movies.data.remote.model.ResultMovieSimilarResponse
import com.desafiom2y.danilo.movies.domain.model.Movie
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

class DTOMovieSimilarGenerate {
    companion object {
        private const val REPOURI = "MovieSimilarResponse.json"
    }
    private var result: ResultMovieSimilarResponse = ResultMovieSimilarResponse()

    init {
        val moshi: Moshi = Moshi.Builder().build()
        val adapter: JsonAdapter<ResultMovieSimilarResponse> = moshi.adapter(ResultMovieSimilarResponse::class.java)
        val jsonString = getStringJson(REPOURI)
        adapter.fromJson(jsonString)?.let {
            result = it
        }
    }

    fun generateMovieSimilarModel(): List<Movie> {
        return result.results.map { it.toData() }
    }

}