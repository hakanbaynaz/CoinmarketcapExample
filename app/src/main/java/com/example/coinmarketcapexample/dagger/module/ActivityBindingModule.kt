package com.example.coinmarketcapexample.dagger.module

import com.example.coinmarketcapexample.activity.MainActivity
import com.example.coinmarketcapexample.dagger.component.AppComponent
import com.example.coinmarketcapexample.dagger.component.MainActivityComponent
import dagger.Binds
import dagger.Component
import dagger.Module
import dagger.android.AndroidInjector
import dagger.multibindings.ClassKey
import dagger.multibindings.IntoMap


@Module(subcomponents = [MainActivityComponent::class])
abstract class ActivityBindingModule {

    @Binds
    @IntoMap
    @ClassKey(value = MainActivity::class)
    internal abstract fun bindMainActivity(builder: MainActivityComponent.Builder): AndroidInjector.Factory<*>
}