package com.fed.pitarestesttask.network

import com.fed.pitarestesttask.model.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("search.json?api-key=8e32ce39c88647eb9fa04fe3c9a3617c")
    fun requestForArticles(@Query("query") query: String?): Call<ApiResponse>
}