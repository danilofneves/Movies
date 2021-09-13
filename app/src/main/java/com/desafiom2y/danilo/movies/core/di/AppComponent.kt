package com.desafiom2y.danilo.movies.core.di

import android.app.Application
import com.desafiom2y.danilo.movies.core.App
import com.desafiom2y.danilo.movies.data.di.DataModule
import com.desafiom2y.danilo.movies.domain.di.DomainModule
import com.desafiom2y.danilo.movies.presentation.di.ActivityModuleBuilder
import com.desafiom2y.danilo.movies.presentation.di.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule


import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModuleBuilder::class,
        ContextModule::class,
        DataModule::class,
        DomainModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application):Builder
        fun build(): AppComponent
    }

    fun inject(app: App)
}