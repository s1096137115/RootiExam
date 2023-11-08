package com.example.rootiexam.api

import com.example.rootiexam.model.news.dto.HeadlinesDto
import retrofit2.http.GET

interface NewsApi {

    @GET("v2/top-headlines?country=us&apiKey=a50dc5a7e34b47cf8d96fe7815e8682b")
    suspend fun fetchHeadlines(): HeadlinesDto
}