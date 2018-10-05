package com.rxchainretrier

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * Non null observe extension, you can read more [here](https://proandroiddev.com/nonnull-livedata-with-kotlin-extension-26963ffd0333)
 */
fun <T> LiveData<T>.nonNullObserve(owner : LifecycleOwner, observer : (t : T) -> Unit) {
    this.observe(owner, Observer {
        it?.let(observer)
    })
}

