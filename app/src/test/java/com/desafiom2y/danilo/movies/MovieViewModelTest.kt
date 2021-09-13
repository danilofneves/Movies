package com.desafiom2y.danilo.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.desafiom2y.danilo.movies.core.DTOMovieGenerate
import com.desafiom2y.danilo.movies.core.DTOMovieSimilarGenerate
import com.desafiom2y.danilo.movies.core.MainCoroutineRule
import com.desafiom2y.danilo.movies.core.getOrAwaitValue
import com.desafiom2y.danilo.movies.domain.usecases.*
import com.desafiom2y.danilo.movies.presentation.viewmodels.MainViewModel
import com.desafiom2y.danilo.movies.data.Resource
import com.desafiom2y.danilo.movies.data.exception.ResponseError
import com.desafiom2y.danilo.movies.domain.model.Movie
import io.mockk.coEvery
import io.mockk.every
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.core.Every
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieViewModelTest {
    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var mainViewModel: MainViewModel

    private lateinit var disfavorMovie: DisfavorMovie
    private lateinit var favoriteMovie: FavoriteMovie
    private lateinit var isFavoriteMovie: IsFavoriteMovie
    private lateinit var getMovie: GetMovie
    private lateinit var getSimilarMovies: GetSimilarMovies

    private val testMovieGenerator: DTOMovieGenerate = DTOMovieGenerate()
    private val testMovieSimilarGenerator: DTOMovieSimilarGenerate = DTOMovieSimilarGenerate()

    @Before
    fun setup() {
        disfavorMovie = spyk()
        favoriteMovie = spyk()
        isFavoriteMovie = spyk()
        getMovie = spyk()
        getSimilarMovies = spyk()
        mainViewModel =
            MainViewModel(
                getMovie, getSimilarMovies, favoriteMovie, disfavorMovie, isFavoriteMovie
            )
    }

    @Test
    fun `get Movie Detail`() {
        val movieTest = testMovieGenerator.generateMovieModel()

        coEvery{ getMovie()} returns Resource.Success(data= movieTest)

        mainViewModel.getMovieDetail()
        mainViewModel.movieLiveData.observeForever { }

        val movie = mainViewModel.movieLiveData.getOrAwaitValue().data
        assert(movieTest == mainViewModel.movieLiveData.getOrAwaitValue().data)
        assert(movie != null)
    }


    @Test
    fun `get Movie Detail IOError`() {
        val error: Resource<Movie> = Resource.DataError(ResponseError.IOErrorException)

        coEvery{ getMovie()} returns error

        mainViewModel.getMovieDetail()
        mainViewModel.movieLiveData.observeForever { }

        assert( mainViewModel.movieLiveData.getOrAwaitValue().error is ResponseError.IOErrorException)
    }

}