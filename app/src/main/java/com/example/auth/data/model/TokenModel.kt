package com.example.auth.data.model

data class TokenModel(
    val token: String,
    val role_code: String,
    val id: Int
)