package com.example.coinmarketcapexample.dagger.module

import android.content.Context
import com.example.coinmarketcapexample.activity.MainActivity
import com.example.coinmarketcapexample.dagger.ActivityContext
import com.example.coinmarketcapexample.dagger.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    @ActivityScope
    @ActivityContext
    fun provideContext(mainActivity: MainActivity): Context = mainActivity

}
