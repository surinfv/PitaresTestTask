package com.fed.pitarestesttask.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * created by Fedor SURIN on 10.02.2018.
 */
object RetroClient {
    private val BASE_URL = "http://api.nytimes.com/svc/movies/v2/reviews/"

    private val retrofitInstance: Retrofit
        get() = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

    val apiService: ApiService
        get() = retrofitInstance.create(ApiService::class.java)


}