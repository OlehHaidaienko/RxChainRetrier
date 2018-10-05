package com.rxchainretrier.view.fragment.albums

import androidx.databinding.ObservableInt
import com.rxchainretrier.base.BaseViewModel
import com.rxchainretrier.network.ApiService
import com.rxchainretrier.provider.AppProvider
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class AlbumViewModel(apiService : ApiService = AppProvider.provideApiService()) : BaseViewModel() {
    val albumsCount = ObservableInt(0)

    init {
        disposable += apiService.getAlbums()
                .subscribeOn(Schedulers.io())
                .subscribeBy(onSuccess = {
                    albumsCount.set(it.size)
                }, onError = {
                    Timber.e(it)
                    messageEvent.postValue(it.message)
                })
    }
}