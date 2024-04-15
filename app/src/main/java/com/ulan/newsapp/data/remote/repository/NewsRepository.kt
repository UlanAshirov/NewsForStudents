package com.ulan.newsapp.data.remote.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ulan.newsapp.core.Resource
import com.ulan.newsapp.data.model.BaseMainResponse
import com.ulan.newsapp.data.remote.apiservice.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class NewsRepository @Inject constructor(private val apiService: ApiService) {

    fun getNews(newsName: String) : MutableLiveData<Resource<BaseMainResponse?>> {
        val liveData = MutableLiveData<Resource<BaseMainResponse?>>()
        liveData.value = Resource.Loading()
        apiService.getNews(newsName = newsName).enqueue(object: Callback<BaseMainResponse>{
            override fun onResponse(
                call: Call<BaseMainResponse>,
                response: Response<BaseMainResponse>
            ) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        liveData.value = Resource.Success(data = response.body())
                    } else {
                        liveData.value = Resource.Error(message = response.message())
                    }
                }
            }

            override fun onFailure(call: Call<BaseMainResponse>, t: Throwable) {
                liveData.value = t.localizedMessage?.let { Resource.Error(message = it) }
            }
        })
        return liveData
    }
}