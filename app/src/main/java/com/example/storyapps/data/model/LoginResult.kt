package com.example.storyapps.data.model

import com.google.gson.annotations.SerializedName

data class LoginResult(
    @field:SerializedName("userId")
    var userId: String? = null,
    @field:SerializedName("name")
    var name: String? = null,
    @field:SerializedName("token")
    var token: String? = null
)
