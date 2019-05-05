package com.example.coinmarketcapexample.repository

import com.example.coinmarketcapexample.model.CoinListDataModel
import com.example.coinmarketcapexample.retrofit.ApiInterface
import com.example.coinmarketcapexample.utils.ApiErrorUtil
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class RetrofitCoinListRepository @Inject constructor(private val apiInterface: ApiInterface) : CoinListRepository(),
    Callback<CoinListDataModel> {

    companion object {
        private const val LIMIT = 40
    }

    private var mLastIndex = 1
    private var mCall: Call<CoinListDataModel>? = null

    override fun loadMore() {
        mLastIndex = getLiveData().value?.data?.size?.plus(1) ?: 1
        fetchData()
    }

    override fun fetchData() {
        getIsLoadingLiveData().value?.let {
            if (it) {
                return//Already fetching data
            }
        }
        getIsLoadingLiveData().value = true

        mCall = apiInterface.getCoinList(mLastIndex, LIMIT)
        mCall?.enqueue(this)
    }

    override fun onFailure(call: Call<CoinListDataModel>, t: Throwable) {
        getIsLoadingLiveData().value = false
        val apiErrorModel = ApiErrorUtil.handleErrorData(t)
        getErrorLiveData().value = apiErrorModel
        t.printStackTrace()
    }

    override fun onResponse(call: Call<CoinListDataModel>, response: Response<CoinListDataModel>) {
        getIsLoadingLiveData().value = false
        if (!response.isSuccessful) {
            val apiErrorModel = ApiErrorUtil.handleErrorData(response.errorBody()?.string())
            getErrorLiveData().value = apiErrorModel
            return
        }
        if (mLastIndex > 1) {// load more case
            val coinListDataModel = getLiveData().value
            coinListDataModel?.apply {
                response.body()?.let {
                    data.addAll(it.data)
                    status = it.status
                    getLiveData().value = this
                }
            }
        } else {
            getLiveData().value = response.body()
        }
    }

    override fun onCleared() {
        mCall?.cancel()
    }
}