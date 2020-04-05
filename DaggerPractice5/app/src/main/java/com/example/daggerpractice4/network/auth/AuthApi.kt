package com.example.daggerpractice4.network.auth

import com.example.daggerpractice4.models.User
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthApi {
    @GET("users/{id}")
    fun getUser(@Path("id") id: Int): Single<User>
}