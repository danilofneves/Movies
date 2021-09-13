package com.desafiom2y.danilo.movies.core.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module
abstract class ContextModule {

    @Binds
    abstract fun providesContext(application: Application):Context
}