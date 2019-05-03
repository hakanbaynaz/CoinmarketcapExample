package com.example.coinmarketcapexample.dagger.component

import com.example.coinmarketcapexample.activity.MainActivity
import com.example.coinmarketcapexample.dagger.ActivityScope
import com.example.coinmarketcapexample.dagger.module.FragmentBindingModule
import com.example.coinmarketcapexample.dagger.module.MainActivityModule
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ActivityScope
@Subcomponent(modules = [MainActivityModule::class, FragmentBindingModule::class])
interface MainActivityComponent : AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<MainActivity>()

}