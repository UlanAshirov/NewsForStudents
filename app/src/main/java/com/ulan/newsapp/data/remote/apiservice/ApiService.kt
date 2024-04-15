package com.ulan.newsapp.data.remote.apiservice

import com.ulan.newsapp.BuildConfig.API_KEY
import com.ulan.newsapp.data.model.BaseMainResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("v2/everything")
    fun getNews(
        @Query("q") newsName: String? = null,
        @Query("apiKey") key: String = API_KEY
    ): Call<BaseMainResponse>

    @GET("v2/top-headlines")
    fun getTopNews(
        @Query("country") country: String = "kgz"
    ): Call<BaseMainResponse>
}