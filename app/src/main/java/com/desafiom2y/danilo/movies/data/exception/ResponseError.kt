package com.desafiom2y.danilo.movies.data.exception

import java.lang.Exception

sealed class ResponseError: Exception(){
    object ClientErrorException: ResponseError()
    object IOErrorException: ResponseError()
    object ServerErrorException: ResponseError()
}