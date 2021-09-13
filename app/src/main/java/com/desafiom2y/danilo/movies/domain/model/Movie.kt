package com.desafiom2y.danilo.movies.domain.model

import java.util.*

data class Movie (
    val id: Long,
    val title: String,
    val popularity: Double,
    val vote_count: Int,
    val release_date: String,
    val poster_path: String,
    var genres: List<Genre> = listOf()
)