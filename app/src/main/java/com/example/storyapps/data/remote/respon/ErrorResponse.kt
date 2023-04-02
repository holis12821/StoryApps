package com.example.storyapps.data.remote.respon

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @field:SerializedName("error")
    var isError: Boolean? = null,
    @field:SerializedName("message")
    var message: String? = null
)
