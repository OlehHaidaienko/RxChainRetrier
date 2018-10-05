package com.rxchainretrier.network

import com.rxchainretrier.network.entity.Album
import com.rxchainretrier.network.entity.User
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    fun getUsers() : Single<List<User>>

    @GET("albums")
    fun getAlbums() : Single<List<Album>>
}