package com.example.coinmarketcapexample.repository

import com.example.coinmarketcapexample.model.CoinDetailDataModel

abstract class CoinDetailRepository : BaseRepository<CoinDetailDataModel>() {
    abstract fun fetchData(id: Int)
}