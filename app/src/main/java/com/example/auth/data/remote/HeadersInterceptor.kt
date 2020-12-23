package com.example.auth.data.remote

import com.example.auth.data.local.PrefsHelper
import okhttp3.Interceptor
import okhttp3.Response

class HeadersInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = PrefsHelper.getToken()
        val request = chain.request().newBuilder()
        if (token.isNotEmpty())
            request.addHeader("token", token)

        return chain.proceed(request.build())
    }
}