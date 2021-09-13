package com.desafiom2y.danilo.movies.domain.model

data class Genre (
    val id: Long,
    var name: String,
){
    override fun toString(): String{
        return this.name
    }
}