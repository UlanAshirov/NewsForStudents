package com.ulan.newsapp.data.model

data class BaseMainResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)