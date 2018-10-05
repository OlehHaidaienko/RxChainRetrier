package com.rxchainretrier.network

import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.rxchainretrier.base.BaseActivity
import com.rxchainretrier.provider.AppProvider
import io.reactivex.*
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.CallAdapter
import timber.log.Timber
import java.lang.reflect.Type

class RxRetryCallAdapter<R>(private val originalAdapter : CallAdapter<R, *>) : CallAdapter<R, Any> {
    override fun adapt(call : Call<R>) : Any {
        val adaptedValue = originalAdapter.adapt(call)
        return when (adaptedValue) {
            is Completable -> {
                adaptedValue.doOnError(this::sendBroadcast)
                        .retryWhen {
                            AppProvider.provideRetrySubject().toFlowable(BackpressureStrategy.LATEST)
                                    .observeOn(Schedulers.io())
                        }
            }
            is Single<*> -> {
                adaptedValue.doOnError(this::sendBroadcast)
                        .retryWhen {
                            AppProvider.provideRetrySubject().toFlowable(BackpressureStrategy.LATEST)
                                    .observeOn(Schedulers.io())
                        }
            }
            is Maybe<*> -> {
                adaptedValue.doOnError(this::sendBroadcast)
                        .retryWhen {
                            AppProvider.provideRetrySubject().toFlowable(BackpressureStrategy.LATEST)
                                    .observeOn(Schedulers.io())
                        }
            }
            is Observable<*> -> {
                adaptedValue.doOnError(this::sendBroadcast)
                        .retryWhen {
                            AppProvider.provideRetrySubject()
                                    .observeOn(Schedulers.io())
                        }
            }
            is Flowable<*> -> {
                adaptedValue.doOnError(this::sendBroadcast)
                        .retryWhen {
                            AppProvider.provideRetrySubject().toFlowable(BackpressureStrategy.LATEST)
                                    .observeOn(Schedulers.io())
                        }
            }
            else -> {
                adaptedValue
            }
        }
    }

    override fun responseType() : Type = originalAdapter.responseType()

    private fun sendBroadcast(throwable : Throwable) {
        Timber.e(throwable)
        LocalBroadcastManager.getInstance(AppProvider.appInstance).sendBroadcast(Intent(BaseActivity.ERROR_ACTION))
    }
}