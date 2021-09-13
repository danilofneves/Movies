package com.desafiom2y.danilo.movies.data.extensions

fun String.getYear(): String{
    return this.split("-").get(0)
}