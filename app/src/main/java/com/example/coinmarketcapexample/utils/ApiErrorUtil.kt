package com.example.coinmarketcapexample.utils

import com.example.coinmarketcapexample.model.ApiErrorModel
import com.example.coinmarketcapexample.model.StatusModel
import com.google.gson.Gson

class ApiErrorUtil {
    companion object {

        private const val NETWORK_ERROR_CODE = -1

        fun handleErrorData(errorData: String?): ApiErrorModel {
            return Gson().fromJson(errorData, ApiErrorModel::class.java)
        }

        fun handleErrorData(errorData: Throwable?): ApiErrorModel {
            return ApiErrorModel(StatusModel(NETWORK_ERROR_CODE, errorData?.message))
        }
    }
}