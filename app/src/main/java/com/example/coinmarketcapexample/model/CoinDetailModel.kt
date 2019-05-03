package com.example.coinmarketcapexample.model

data class CoinDetailModel(
    val id: Int,
    val urls: UrlsModel,
    val logo: String,
    val name: String,
    val symbol: String,
    val description: String?
)
