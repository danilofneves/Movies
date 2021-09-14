package com.desafiom2y.danilo.movies.presentation.extensions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
    liveData.observe(
        this,
        { it?.let { t -> action(t) } }
    )
}
