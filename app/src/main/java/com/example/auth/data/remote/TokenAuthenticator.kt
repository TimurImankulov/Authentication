package com.example.auth.data.remote

import com.example.auth.data.local.PrefsHelper
import com.example.auth.data.model.TokenModel
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class TokenAuthenticator(private val authApi: AuthApi) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {

        if (response.code == 401) {
            PrefsHelper.saveToken("")
            val result = refreshToken()
            return if (result.isSuccessful && result.body() != null) {
                val token = result.body()?.token ?: ""
                token?.let { PrefsHelper.saveToken(it) }
                response.request.newBuilder()
                    .addHeader("token", token)
                    .build()
            } else {
                null
            }
        }

        return null
    }

    private fun refreshToken(): retrofit2.Response<TokenModel> {
        return runBlocking {
            val map = mapOf(
                "email" to "rojsasha@gmail.com",
                "password" to "fifa11alex"
            )
            val result = authApi.login(map)

            return@runBlocking result
        }
    }
}