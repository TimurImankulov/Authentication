package com.example.auth.data.remote

import com.example.auth.data.model.TokenModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("api/v1/users/auth")
    suspend fun login(@Body data: Map<String, String>): Response<TokenModel>
}
