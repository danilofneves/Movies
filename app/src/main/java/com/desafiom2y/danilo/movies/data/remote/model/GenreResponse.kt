package com.desafiom2y.danilo.movies.data.remote.model

import com.desafiom2y.danilo.movies.domain.model.Genre

data class GenreResponse (
    val id: Long,
    val name: String,
){
    fun toData(): Genre {
        return Genre(
            id = id,
            name = name
        )
    }
}