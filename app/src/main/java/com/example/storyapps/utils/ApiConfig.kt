package com.example.storyapps.utils

import androidx.viewbinding.BuildConfig
import com.example.storyapps.data.remote.datasource.StoryDataSource
import com.example.storyapps.data.remote.datasource.UserDataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConfig {
    private val loggingInterceptor = if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    } else {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun getUserService(): UserDataSource {
        return retrofit.create(UserDataSource::class.java)
    }

    fun getStoryService(): StoryDataSource {
        return retrofit.create(StoryDataSource::class.java)
    }
}