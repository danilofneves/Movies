package com.desafiom2y.danilo.movies.data.local.datasource

import javax.inject.Inject


class SharedDataSourceImpl @Inject constructor(private val preferences: Preferences): SharedDataSource {

    override fun isFavorite(): Boolean {
        return preferences.isFavorite()
    }

    override fun favorite() {
        preferences.favorite()
    }

    override fun disfavor() {
        preferences.disfavor()
    }

}