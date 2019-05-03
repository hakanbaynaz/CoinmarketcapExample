package com.example.coinmarketcapexample.repository

import androidx.lifecycle.MutableLiveData
import com.example.coinmarketcapexample.model.ApiErrorModel
import com.example.coinmarketcapexample.model.BaseDataModel

abstract class BaseRepository<T : BaseDataModel> {

    private val mLiveData = MutableLiveData<T>()
    private val mIsLoadingLiveData = MutableLiveData<Boolean>()
    private val mErrorLiveData = MutableLiveData<ApiErrorModel>()

    open fun getLiveData() = mLiveData

    open fun getErrorLiveData() = mErrorLiveData

    open fun getIsLoadingLiveData() = mIsLoadingLiveData

}