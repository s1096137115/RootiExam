package com.example.rootiexam.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rootiexam.model.news.vo.ArticleVo
import com.example.rootiexam.repository.NewsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainViewModel(private val newsRepository: NewsRepository) : ViewModel() {

    val list = MutableLiveData<List<ArticleVo>>()

    @OptIn(ExperimentalCoroutinesApi::class)
    fun initData() {
        viewModelScope.launch {
            newsRepository.clearHeadlines()
            newsRepository.fetchHeadlines()
                .map { newsRepository.saveHeadlines(it) }
                .flatMapConcat { newsRepository.observeHeadlines() }
                .map { it.map { it.asVo() } }
                .collectLatest {
                    list.postValue(it)
                }
        }
    }

}