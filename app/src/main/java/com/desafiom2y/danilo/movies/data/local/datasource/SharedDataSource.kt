package com.desafiom2y.danilo.movies.data.local.datasource

interface SharedDataSource {

    fun isFavorite(): Boolean
    fun favorite()
    fun disfavor()
}