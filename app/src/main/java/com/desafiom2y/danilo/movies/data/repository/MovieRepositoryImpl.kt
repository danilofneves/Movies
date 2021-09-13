package com.desafiom2y.danilo.movies.data.repository


import androidx.paging.*
import com.desafiom2y.danilo.movies.data.local.datasource.SharedDataSource
import com.desafiom2y.danilo.movies.data.remote.apidatasource.MovieApiDataSource
import com.desafiom2y.danilo.movies.data.remote.apidatasource.retrofit.MoviePagingApiDataSourceRetro
import com.desafiom2y.danilo.movies.domain.model.Genre
import com.desafiom2y.danilo.movies.domain.model.Movie
import com.desafiom2y.danilo.movies.domain.repository.MovieRepository
import com.desafiom2y.danilo.movies.data.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor (
    private val sharedDataSource: SharedDataSource,
    private val movieApiDataSource: MovieApiDataSource,
    private val moviePagingApiDataSourceRetro: MoviePagingApiDataSourceRetro):
    MovieRepository {
    companion object {
        private const val PAGE_SIZE = 20
    }

    override fun favorite() {
        sharedDataSource.favorite()
    }

    override fun disfavor() {
        sharedDataSource.disfavor()
    }

    override fun isFavorite(): Boolean {
        return sharedDataSource.isFavorite()
    }

    override suspend fun getMovie(): Resource<Movie> {
        return movieApiDataSource.getMovie()
    }

    override suspend fun getSimilarMovies(): Flow<PagingData<Movie>> {
        val genres = movieApiDataSource.getAllGenres()
        return Pager(config = PagingConfig(pageSize = PAGE_SIZE)) {
            moviePagingApiDataSourceRetro
        }.flow.map { it.setGener(genres.data)}
    }

    private fun PagingData<Movie>.setGener(list: List<Genre>?):PagingData<Movie>{
        return if (list == null) this
        else this.map { movies ->
                list.forEach { genre ->
                    movies.genres.find { movieGener ->
                        movieGener.id == genre.id
                    }?.name = genre.name
                }
                movies
        }
    }
}

