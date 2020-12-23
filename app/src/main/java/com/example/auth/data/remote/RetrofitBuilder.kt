package com.example.auth.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {

    private const val TIME_INTERVAL = 15L

    fun initRetrofit(baseUrl: String): RetrofitService {
        return Builder()
            .baseUrl(baseUrl)
            .client(initOkHttpClient(baseUrl))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RetrofitService::class.java)
    }

    private fun initOkHttpClient(baseUrl: String): OkHttpClient {
        val okHttp = OkHttpClient.Builder()
            .addInterceptor(HeadersInterceptor())
            .authenticator(TokenAuthenticator(authApi(baseUrl))) //если TokenAuthenticator не отрабатывает тогда что происходит
            .writeTimeout(TIME_INTERVAL, TimeUnit.SECONDS)
            .readTimeout(TIME_INTERVAL, TimeUnit.SECONDS)
            .connectTimeout(TIME_INTERVAL, TimeUnit.SECONDS)

        okHttp.addInterceptor(provideLogginingInterceptor())

        return okHttp.build()
    }

    private fun authApi(baseUrl: String): AuthApi {
        return Builder()
            .baseUrl(baseUrl)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(provideLogginingInterceptor()).build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthApi::class.java)
    }

    private fun provideLogginingInterceptor(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        return logger
    }
}
