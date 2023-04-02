package com.example.storyapps.data.repository

import android.content.Context
import com.example.storyapps.data.local.UserPreference
import com.example.storyapps.data.model.LoginResult
import com.example.storyapps.data.model.UserEntity
import com.example.storyapps.data.remote.datasource.UserDataSource
import com.example.storyapps.data.remote.respon.BaseResponse
import com.example.storyapps.data.remote.respon.RegisterResponse
import com.example.storyapps.utils.get
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepository(
    private val userDataSource: UserDataSource,
    private val prefs: UserPreference
) {

    suspend fun login(email: String, password: String): Flow<LoginResult> {
        return flow {
            val response = userDataSource.login(email, password)
            if (response.isSuccessful) {
                val body = response.body()
                emit(body?.data!!)
            } else {
                val typeToken = object : TypeToken<BaseResponse<LoginResult>?>() {}.type
                val errorBody = response.errorBody().get<LoginResult>(typeToken)
                val data = errorBody.data
                if (data != null) {
                    emit(data)
                } else {
                    val message = errorBody.message
                    error(message!!)
                }
            }
        }
    }

    suspend fun register(
        name: String, email: String, password: String
    ): Flow<RegisterResponse> {
        return flow {
            val response =userDataSource.register(name, email, password)
            if (response.isSuccessful) {
                val body = response.body()
                emit(body!!)
            } else {
                val errorBody = response.errorBody().get()
                val message = errorBody.message
                error(message!!)
            }
        }
    }
}