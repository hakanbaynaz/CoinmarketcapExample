package com.example.coinmarketcapexample.dagger.module

import android.app.Application
import android.content.Context
import com.example.coinmarketcapexample.dagger.ApplicationContext
import com.example.coinmarketcapexample.dagger.ApplicationScope
import com.example.coinmarketcapexample.imageloader.GlideImageLoader
import com.example.coinmarketcapexample.imageloader.ImageLoader
import dagger.Module
import dagger.Provides

@Module(includes = [ViewModelModule::class, RetrofitModule::class, RepositoryModule::class])
class AppModule {

    @Provides
    @ApplicationContext
    @ApplicationScope
    fun provideContext(application: Application): Context = application

    @Provides
    @ApplicationScope
    fun provideImageLoader(glideImageLoader: GlideImageLoader): ImageLoader = glideImageLoader
}
