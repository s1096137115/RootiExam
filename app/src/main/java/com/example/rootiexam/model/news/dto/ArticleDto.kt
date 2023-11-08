package com.example.rootiexam.model.news.dto

import com.example.rootiexam.model.news.dao.ArticleDao
import kotlinx.serialization.Serializable

@Serializable
data class ArticleDto(
    val source: SourceDto,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String,
    val title: String,
    val url: String,
    val urlToImage: String?
) {
    fun asDao() = ArticleDao(source.id, source.name, author, content, description, publishedAt, title, url, urlToImage)
}