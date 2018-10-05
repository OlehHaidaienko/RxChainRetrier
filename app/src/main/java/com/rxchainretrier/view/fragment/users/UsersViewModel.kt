package com.rxchainretrier.view.fragment.users

import androidx.databinding.ObservableInt
import com.rxchainretrier.base.BaseViewModel
import com.rxchainretrier.network.ApiService
import com.rxchainretrier.provider.AppProvider
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class UsersViewModel(apiService : ApiService = AppProvider.provideApiService()) : BaseViewModel() {
    val usersCount = ObservableInt(0)

    init {
        disposable += apiService.getUsers()
                .subscribeOn(Schedulers.io())
                .subscribeBy(onSuccess = {
                    usersCount.set(it.size)
                }, onError = {
                    Timber.e(it)
                    messageEvent.postValue(it.message)
                })
    }
}