package com.example.squarerepos.core.di.module

import com.example.squarerepos.BuildConfig
import com.example.squarerepos.data.SquareRepository
import com.example.squarerepos.network.api.SquareReposAPI
import com.example.squarerepos.network.repository.SquareRepositoryImp
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single<Interceptor> { provideHttpLoggingInterceptor() }
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<Interceptor>())
            .build()
    }
    single {
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }
    single { get<Retrofit>().create<SquareReposAPI>(SquareReposAPI::class.java) }

    //Repositories
    single<SquareRepository> { SquareRepositoryImp(get())  }
}

private fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
    else HttpLoggingInterceptor.Level.NONE
    return httpLoggingInterceptor
}