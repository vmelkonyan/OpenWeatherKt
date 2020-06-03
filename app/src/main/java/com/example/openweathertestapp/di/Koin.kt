package com.example.openweathertestapp.di

import com.example.openweathertestapp.MainViewModel
import com.example.openweathertestapp.dao.SQLiteDatabaseHandler
import com.example.openweathertestapp.repo.WeatherRepo
import com.example.openweathertestapp.service.RetrofitClientInstance
import com.example.openweathertestapp.service.StorageServiceImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModel = module {
    viewModel { MainViewModel(get(), get()) }
}

val storageModule = module {
    factory { SQLiteDatabaseHandler(get()) }
}

val repoModule = module {
    single { StorageServiceImpl(get()) }
    single { WeatherRepo(get()) }
}

val apiModule = module {
    single {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
    }
    single { RetrofitClientInstance.getClient(client = get()) }
}
