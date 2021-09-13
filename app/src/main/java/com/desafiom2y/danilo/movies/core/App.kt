package com.desafiom2y.danilo.movies.core

import androidx.multidex.MultiDexApplication
import com.desafiom2y.danilo.movies.core.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class App : MultiDexApplication(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    fun initDagger() {
        DaggerAppComponent.builder().application(this).build().inject(this)
    }

}