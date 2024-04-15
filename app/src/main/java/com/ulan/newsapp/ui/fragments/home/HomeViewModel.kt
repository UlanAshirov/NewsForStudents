package com.ulan.newsapp.ui.fragments.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ulan.newsapp.core.Resource
import com.ulan.newsapp.data.model.BaseMainResponse
import com.ulan.newsapp.data.remote.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private var _liveData = MutableLiveData<Resource<BaseMainResponse?>>()
    val liveData get() = _liveData

    fun getNews(newsName: String) {
        _liveData = repository.getNews(newsName = newsName)
    }
}