package com.example.rootiexam.model.news.dao

import com.example.rootiexam.model.news.vo.ArticleVo
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class ArticleDao(
    var id: String? = null,
    var name: String = "",
    var author: String? = null,
    var content: String? = null,
    var description: String? = null,
    var publishedAt: String = "",
    var title: String = "",
    @PrimaryKey
    var url: String = "",
    var urlToImage: String? = null
) : RealmObject {
    constructor() : this(url = "") {
    }

    fun asVo() = ArticleVo(id, name, author, content, description, publishedAt, title, url, urlToImage)
}