package com.example.coinmarketcapexample.repository

import com.example.coinmarketcapexample.model.CoinDetailDataModel
import com.example.coinmarketcapexample.retrofit.ApiInterface
import com.example.coinmarketcapexample.utils.ApiErrorUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class RetrofitCoinDetailRepository @Inject constructor(private val apiInterface: ApiInterface) :
    CoinDetailRepository() {

    override fun fetchData(id: Int) {
        getIsLoadingLiveData().value?.let {
            if (it) {
                return//Already fetching data
            }
        }
        getIsLoadingLiveData().value = true

        apiInterface.getCoinDetails(id).enqueue(object : Callback<CoinDetailDataModel> {
            override fun onFailure(call: Call<CoinDetailDataModel>, t: Throwable) {
                getIsLoadingLiveData().value = false
                val apiErrorModel = ApiErrorUtil.handleErrorData(t)
                getErrorLiveData().value = apiErrorModel
                t.printStackTrace()
            }

            override fun onResponse(call: Call<CoinDetailDataModel>, response: Response<CoinDetailDataModel>) {
                getIsLoadingLiveData().value = false
                if (!response.isSuccessful) {
                    val apiErrorModel = ApiErrorUtil.handleErrorData(response.errorBody()?.string())
                    getErrorLiveData().value = apiErrorModel
                    return
                }
                getLiveData().value = response.body()
            }
        })
    }
}

