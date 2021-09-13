package com.desafiom2y.danilo.movies.data.remote.helper

import com.desafiom2y.danilo.movies.data.extensions.getThrowable
import com.desafiom2y.danilo.movies.data.exception.ResponseError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException

suspend fun safeCall(call: suspend () -> Response<*>): Any? {
    return withContext(Dispatchers.IO) {
        try {
            val response = call.invoke()
            if (response.isSuccessful) {
                response.body()
            } else {
                response.getThrowable()
            }
        }
        catch (e:IOException){
            ResponseError.IOErrorException
        }
    }
}