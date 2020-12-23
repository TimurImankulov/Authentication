package com.example.auth

import android.app.Application
import com.example.auth.data.local.PrefsHelper
import com.example.auth.di.appModules
import org.koin.android.ext.android.startKoin

class AuthApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, appModules)
        PrefsHelper.init(this)
    }
}