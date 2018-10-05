package com.rxchainretrier.provider

import android.app.Application
import com.rxchainretrier.BuildConfig
import com.rxchainretrier.network.ApiService
import com.rxchainretrier.network.RxRetryCallAdapterFactory
import io.reactivex.subjects.PublishSubject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object AppProvider {
    private val subject = PublishSubject.create<Unit>()

    private val apiService : ApiService by lazy {
        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxRetryCallAdapterFactory.create())
                .build()
        return@lazy retrofit.create(ApiService::class.java)
    }

    lateinit var appInstance : Application

    fun provideRetrySubject() : PublishSubject<Unit> = subject

    fun provideApiService() : ApiService = apiService
}