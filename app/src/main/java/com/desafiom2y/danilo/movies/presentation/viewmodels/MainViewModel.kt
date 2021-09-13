package com.desafiom2y.danilo.movies.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.desafiom2y.danilo.movies.data.exception.ResponseError
import com.desafiom2y.danilo.movies.domain.model.Movie
import com.desafiom2y.danilo.movies.domain.usecases.*
import com.desafiom2y.danilo.movies.data.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getMovie: GetMovie,
    private val getSimilarMovies: GetSimilarMovies,
    private val favoriteMovie: FavoriteMovie,
    private val disfavorMovie: DisfavorMovie,
    private val isFavoriteMovie: IsFavoriteMovie
): ViewModel(){

    private val _movieLiveData: MutableLiveData<Resource<Movie>> = MutableLiveData()
    val movieLiveData: LiveData<Resource<Movie>> get() = _movieLiveData
    val error = MutableLiveData<ResponseError>()

    fun getMovieDetail(){
        viewModelScope.launch {
            _movieLiveData.value = Resource.Loading()
            _movieLiveData.value = getMovie()
        }
    }

    fun favorite() = favoriteMovie()
    fun disfavorite() = disfavorMovie()
    fun isFavorite():Boolean = isFavoriteMovie()

    suspend fun moviesSimilarPaging() = getSimilarMovies.invoke().cachedIn(viewModelScope)

    fun notifyFailure(responseError: ResponseError?) {
        error.postValue(responseError!!)
    }

}