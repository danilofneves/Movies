package com.desafiom2y.danilo.movies.data.remote.model

data class ResultMovieSimilarResponse(
    val page: Int = 0,
    val results: List<MovieSimilarResponse> = listOf(),
    val total_pages: Int = 0,
    val total_results: Int = 0
)