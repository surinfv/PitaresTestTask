package com.fed.pitarestesttask.network

import com.fed.pitarestesttask.model.POJO.Response
import retrofit2.Call
import retrofit2.http.GET

/**
 * created by Fedor SURIN on 10.02.2018.
 */
interface ApiService {
    @GET("search.json?api-key=8e32ce39c88647eb9fa04fe3c9a3617c")
    fun requestForArticles(): Call<Response>
}