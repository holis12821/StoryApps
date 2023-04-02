package com.example.storyapps.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.storyapps.data.model.UserEntity
import com.example.storyapps.utils.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreference private constructor(private val dataStore: DataStore<Preferences>) {

    fun getUser(): Flow<UserEntity> {
        return dataStore.data.map { preferences ->
            UserEntity(
                id = preferences[KEY_ID],
                email = preferences[KEY_EMAIL],
                name = preferences[KEY_NAME],
                token = preferences[KEY_TOKEN],
                password = preferences[KEY_PASSWORD],
                isLoggedIn = preferences[KEY_IS_LOGGED_IN]
            )
        }
    }

    suspend fun saveUser(userEntity: UserEntity) {
        dataStore.edit { preferences ->
            preferences[KEY_ID] = userEntity.id ?: ""
            preferences[KEY_EMAIL] = userEntity.email ?: ""
            preferences[KEY_NAME] = userEntity.name ?: ""
            preferences[KEY_TOKEN] = userEntity.token ?: ""
            preferences[KEY_PASSWORD] = userEntity.password ?: ""
            preferences[KEY_IS_LOGGED_IN] = userEntity.isLoggedIn ?: false
        }
    }

    suspend fun updateUser(userEntity: UserEntity) {
        dataStore.edit { preferences ->
            userEntity.id?.let { preferences[KEY_ID] = it }
            userEntity.email?.let { preferences[KEY_EMAIL] = it }
            userEntity.name?.let { preferences[KEY_NAME] = it }
            userEntity.token?.let { preferences[KEY_TOKEN] = it }
            userEntity.password?.let { preferences[KEY_PASSWORD] = it }
            userEntity.isLoggedIn?.let { preferences[KEY_IS_LOGGED_IN] = it }
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserPreference? = null

        fun getInstance(dataStore: DataStore<Preferences>): UserPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}