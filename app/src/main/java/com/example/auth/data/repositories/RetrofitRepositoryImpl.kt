package com.example.auth.data.repositories

import androidx.lifecycle.LiveData
import com.example.auth.data.interactors.RetrofitInteractor
import com.example.auth.data.local.PrefsHelper
import com.example.auth.data.local.ProfileDao
import com.example.auth.data.model.ProfileModel
import com.example.auth.data.model.TokenModel
import retrofit2.Response

interface RetrofitRepository {
    suspend fun login(email: String, password: String): Response<TokenModel>
    suspend fun loadProfile()
    fun getProfile(): LiveData<ProfileModel>
}

class RetrofitRepositoryImpl(
    private val network: RetrofitInteractor,
    private val profileDao: ProfileDao
) : RetrofitRepository {

    override suspend fun login(email: String, password: String): Response<TokenModel> {
        val result = network.login(email = email, password = password)

        result.body()?.token?.let { PrefsHelper.saveToken(it) }
        return result
    }

    override suspend fun loadProfile() {
        val result = network.getProfile()
        result.body()?.let { profileDao.saveProfile(it) }
    }

    override fun getProfile() = profileDao.getProfile()
}