package com.example.coinmarketcapexample

import com.example.coinmarketcapexample.dagger.component.AppComponent
import com.example.coinmarketcapexample.dagger.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class CoinMarketApplication : DaggerApplication() {
    lateinit var appComponent: AppComponent

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        appComponent = DaggerAppComponent.builder().application(this).build()
        return appComponent
    }
}