package com.example.rootiexam.model.news.vo

import kotlinx.serialization.Serializable

@Serializable
data class ArticleVo(
    val id: String?,
    val name: String,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String,
    val title: String,
    val url: String,
    val urlToImage: String?
)