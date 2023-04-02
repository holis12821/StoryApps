package com.example.storyapps.data.remote.respon

import com.example.storyapps.data.model.LoginResult
import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @field:SerializedName("error")
    var isError: Boolean? = null,
    @field:SerializedName("message")
    var message: String? = null,
    @field:SerializedName("loginResult")
    var data: T? = null
)
