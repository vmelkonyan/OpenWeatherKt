package com.example.openweathertestapp.di

import com.example.openweathertestapp.dao.SQLiteDatabaseHandler
import com.example.openweathertestapp.service.RetrofitClientInstance
import com.example.openweathertestapp.service.StorageServiceImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module


val appModule = module {
   //todo
}

val repoModule = module {
    factory { SQLiteDatabaseHandler(get()) }
    single { StorageServiceImpl(get()) }
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
