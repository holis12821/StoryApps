package com.example.storyapps.data.remote.datasource

import com.example.storyapps.data.model.LoginResult
import com.example.storyapps.data.remote.respon.BaseResponse
import com.example.storyapps.data.remote.respon.RegisterResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserDataSource {

    @FormUrlEncoded
    @POST("/v1/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<BaseResponse<LoginResult>>

    @FormUrlEncoded
    @POST("/v1/register")
    suspend fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<RegisterResponse>
}