package com.example.auth.di

import androidx.room.Room
import com.example.auth.BuildConfig.BASE_URL
import com.example.auth.data.interactors.RetrofitInteractor
import com.example.auth.data.interactors.RetrofitInteractorImpl
import com.example.auth.data.local.AppDataBase
import com.example.auth.data.local.AppDataBase.Companion.MIGRATION_1_2
import com.example.auth.data.local.DATABASE_NAME
import com.example.auth.data.remote.RetrofitBuilder
import com.example.auth.data.repositories.RetrofitRepository
import com.example.auth.data.repositories.RetrofitRepositoryImpl
import com.example.auth.ui.main.AuthViewModel
import com.example.auth.ui.main.newActivity.NewViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {
    viewModel { AuthViewModel(get()) }
    viewModel { NewViewModel(get()) }
}

val repositoryModule = module {
    single { RetrofitBuilder.initRetrofit(BASE_URL) }
    single <RetrofitInteractor>{ RetrofitInteractorImpl(get()) }
    single <RetrofitRepository>{ RetrofitRepositoryImpl(get(), get()) }


    single {
        Room.databaseBuilder(
            get(),
            AppDataBase::class.java,
            DATABASE_NAME
        )
            .addMigrations(MIGRATION_1_2)
            .build()
    }
    single { get<AppDataBase>().profileDao() }

}

val appModules = listOf(viewModelModule, repositoryModule)