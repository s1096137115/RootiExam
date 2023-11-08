package com.example.rootiexam.base

import android.app.Application
import com.example.rootiexam.api.NewsApi
import com.example.rootiexam.model.news.dao.ArticleDao
import com.example.rootiexam.repository.NewsRepository
import com.example.rootiexam.ui.MainViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(utilModule, viewModelModule, repositoryModule)
        }
    }
}

val utilModule = module {
    single {
        val contentType = "application/json".toMediaType()
        Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory(contentType))
            .baseUrl("https://newsapi.org/")
            .build()
            .create(NewsApi::class.java)
    }
    single {
        val config = RealmConfiguration.create(schema = setOf(ArticleDao::class))
        Realm.open(config)
    }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
}

val repositoryModule = module {
    factory { NewsRepository(get(), get()) }
}