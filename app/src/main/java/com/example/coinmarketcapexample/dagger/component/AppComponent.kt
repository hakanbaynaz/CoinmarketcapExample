package com.example.coinmarketcapexample.dagger.component

import android.app.Application
import com.example.coinmarketcapexample.CoinMarketApplication
import com.example.coinmarketcapexample.dagger.ApplicationScope
import com.example.coinmarketcapexample.dagger.module.ActivityBindingModule
import com.example.coinmarketcapexample.dagger.module.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector


@ApplicationScope
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityBindingModule::class])
interface AppComponent : AndroidInjector<CoinMarketApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }
}