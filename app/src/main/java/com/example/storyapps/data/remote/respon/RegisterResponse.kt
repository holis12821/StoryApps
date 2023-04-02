package com.example.storyapps.data.remote.respon

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
    @field:SerializedName("error")
    var error: Boolean? = null,
    @field:SerializedName("message")
    var message: String? = null
)