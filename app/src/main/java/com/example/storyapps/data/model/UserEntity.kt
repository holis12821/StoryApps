package com.example.storyapps.data.model

data class UserEntity(
    var id: String? = null,
    var name: String? = null,
    var email: String? = null,
    var password: String? = null,
    var token: String? = null,
    var isLoggedIn: Boolean? = null
)
