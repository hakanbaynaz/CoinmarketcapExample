package com.example.coinmarketcapexample.model

import com.google.gson.annotations.SerializedName


data class StatusModel(
    @SerializedName("error_code") val errorCode: Int?, @SerializedName("error_message") val errorMessage: String?
)
