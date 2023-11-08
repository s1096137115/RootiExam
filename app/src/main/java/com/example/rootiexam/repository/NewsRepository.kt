package com.example.rootiexam.repository

import com.example.rootiexam.api.NewsApi
import com.example.rootiexam.model.news.dao.ArticleDao
import com.example.rootiexam.model.news.dto.ArticleDto
import com.example.rootiexam.model.news.dto.HeadlinesDto
import io.realm.kotlin.Realm
import io.realm.kotlin.delete
import io.realm.kotlin.notifications.InitialResults
import io.realm.kotlin.notifications.ResultsChange
import io.realm.kotlin.notifications.UpdatedResults
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class NewsRepository(private val newsApi: NewsApi, private val realm: Realm) {

    suspend fun fetchHeadlines() = flowOf(newsApi.fetchHeadlines())

    fun clearHeadlines() {
        realm.writeBlocking {
            delete<ArticleDao>()
        }
    }

    fun saveHeadlines(response: HeadlinesDto) {
        realm.writeBlocking {
            response.articles
                .map { it.asDao() }
                .onEach { copyToRealm(it) }
        }
    }

    fun observeHeadlines() = realm
        .query(ArticleDao::class)
        .asFlow()
        .map { changes: ResultsChange<ArticleDao> ->
            when (changes) {
                is InitialResults -> {
                    changes.list
                }

                is UpdatedResults -> {
                    changes.list
                }
            }
        }
}