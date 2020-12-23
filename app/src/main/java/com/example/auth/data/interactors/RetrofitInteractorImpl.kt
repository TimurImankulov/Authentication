package com.example.auth.data.interactors

import com.example.auth.data.model.ProfileModel
import com.example.auth.data.model.TokenModel
import com.example.auth.data.remote.RetrofitService
import retrofit2.Response

interface RetrofitInteractor {
    suspend fun login(email: String, password: String): Response<TokenModel>
    suspend fun getProfile(): Response<ProfileModel?>
}

class RetrofitInteractorImpl(private val service: RetrofitService) : RetrofitInteractor {

    override suspend fun login(email: String, password: String): Response<TokenModel> {
        val map = mapOf(
            "email" to email,
            "password" to password
        )

        return service.login(map)
    }

    override suspend fun getProfile(): Response<ProfileModel?> {
        return service.loadUserProfile()
    }
}