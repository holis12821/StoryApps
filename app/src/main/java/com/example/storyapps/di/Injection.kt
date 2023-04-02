package com.example.storyapps.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.storyapps.data.local.UserPreference
import com.example.storyapps.data.repository.UserRepository
import com.example.storyapps.utils.ApiConfig

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

object Injection {
    fun provideUserRepository(context: Context): UserRepository {
        val userService = ApiConfig.getUserService()
        return UserRepository(userService, UserPreference.getInstance(context.dataStore))
    }
}