package com.example.coinmarketcapexample.repository

import com.example.coinmarketcapexample.model.CoinListDataModel

abstract class CoinListRepository : BaseRepository<CoinListDataModel>() {
    abstract fun loadMore()
    abstract fun fetchData()
}