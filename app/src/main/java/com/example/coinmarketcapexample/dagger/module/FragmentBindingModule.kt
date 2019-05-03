package com.example.coinmarketcapexample.dagger.module

import com.example.coinmarketcapexample.dagger.FragmentScope
import com.example.coinmarketcapexample.fragment.CoinDetailFragment
import com.example.coinmarketcapexample.fragment.CoinListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class FragmentBindingModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeCoinListFragment(): CoinListFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributeCoinDetailFragment(): CoinDetailFragment
}