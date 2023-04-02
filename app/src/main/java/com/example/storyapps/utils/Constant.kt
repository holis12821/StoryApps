package com.example.storyapps.utils

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

const val BASE_URL = "https://story-api.dicoding.dev"

//Request Code


//Result Code



//Preferences
val KEY_ID = stringPreferencesKey("id")
val KEY_EMAIL = stringPreferencesKey("email")
val KEY_NAME = stringPreferencesKey("name")
val KEY_PASSWORD = stringPreferencesKey("password")
val KEY_TOKEN = stringPreferencesKey("token")
val KEY_IS_LOGGED_IN = booleanPreferencesKey("state")

