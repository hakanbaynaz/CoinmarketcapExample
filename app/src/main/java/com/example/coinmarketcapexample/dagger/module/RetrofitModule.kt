package com.example.coinmarketcapexample.dagger.module

import com.example.coinmarketcapexample.dagger.ApplicationScope
import com.example.coinmarketcapexample.retrofit.ApiInterface
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Module
class RetrofitModule {

    private val baseUrl = "https://pro-api.coinmarketcap.com"

    @Provides
    @ApplicationScope
    fun getApiInterface(retrofit: Retrofit): ApiInterface = retrofit.create(ApiInterface::class.java)

    @Provides
    @ApplicationScope
    fun getRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    @ApplicationScope
    fun getOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpLoggingInterceptor)
        .build()

    @Provides
    @ApplicationScope
    fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        return httpLoggingInterceptor
    }
}