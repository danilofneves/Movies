package com.desafiom2y.danilo.movies.presentation.extensions

import com.desafiom2y.danilo.movies.R
import com.desafiom2y.danilo.movies.databinding.ActivityMainBinding

fun ActivityMainBinding.favorite(){
    this.ivFavorite.setImageResource(R.drawable.favorite_full_24)
}

fun ActivityMainBinding.disfavorite(){
    this.ivFavorite.setImageResource(R.drawable.favorite_empty_24)
}
