package com.example.rootiexam.model.news.dto

import kotlinx.serialization.Serializable

@Serializable
data class HeadlinesDto(
    val articles: List<ArticleDto>,
    val status: String,
    val totalResults: Int
)