package com.example.coinmarketcapexample.retrofit

import com.example.coinmarketcapexample.model.CoinDetailDataModel
import com.example.coinmarketcapexample.model.CoinListDataModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface ApiInterface {

    companion object {
        private const val API_KEY = "Place your api key here"
    }

    @Headers("X-CMC_PRO_API_KEY: $API_KEY")
    @GET("/v1/cryptocurrency/listings/latest?")
    fun getCoinList(@Query("start") start: Int, @Query("limit") limit: Int): Call<CoinListDataModel>

    @Headers("X-CMC_PRO_API_KEY: $API_KEY")
    @GET("/v1/cryptocurrency/info?")
    fun getCoinDetails(@Query("id") id: Int): Call<CoinDetailDataModel>

}