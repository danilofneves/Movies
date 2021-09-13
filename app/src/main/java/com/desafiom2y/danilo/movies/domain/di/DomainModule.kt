package com.desafiom2y.danilo.movies.domain.di

import com.desafiom2y.danilo.movies.domain.repository.MovieRepository
import com.desafiom2y.danilo.movies.domain.usecases.*
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideGetMovie(movieRepository: MovieRepository): GetMovie = GetMovieImpl(movieRepository)

    @Provides
    fun provideSimilarMovie(movieRepository: MovieRepository): GetSimilarMovies = GetSimilarMoviesImpl(movieRepository)

    @Provides
    fun provideFavoriteMovie(movieRepository: MovieRepository): FavoriteMovie = FavoriteMovieImpl(movieRepository)

    @Provides
    fun provideDisfavorMovie(movieRepository: MovieRepository): DisfavorMovie = DisfavorMovieImpl(movieRepository)

    @Provides
    fun provideIsFavoriteMovie(movieRepository: MovieRepository): IsFavoriteMovie = IsFavoriteMovieImpl(movieRepository)

}