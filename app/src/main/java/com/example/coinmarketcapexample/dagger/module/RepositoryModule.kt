package com.example.coinmarketcapexample.dagger.module

import com.example.coinmarketcapexample.repository.CoinDetailRepository
import com.example.coinmarketcapexample.repository.CoinListRepository
import com.example.coinmarketcapexample.repository.RetrofitCoinDetailRepository
import com.example.coinmarketcapexample.repository.RetrofitCoinListRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindCoinListRepository(retrofitCoinListRepository: RetrofitCoinListRepository): CoinListRepository

    @Binds
    abstract fun bindCoinDetailRepository(retrofitCoinDetailRepository: RetrofitCoinDetailRepository): CoinDetailRepository
}