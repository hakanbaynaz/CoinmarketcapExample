package com.example.coinmarketcapexample.viewmodel

import androidx.lifecycle.ViewModel
import com.example.coinmarketcapexample.repository.CoinDetailRepository
import javax.inject.Inject

class CoinDetailViewModel @Inject constructor(private val coinDetailRepository: CoinDetailRepository) : ViewModel() {

    fun getLiveData() = coinDetailRepository.getLiveData()

    fun getErrorLiveData() = coinDetailRepository.getErrorLiveData()

    fun getLoadingLiveData() = coinDetailRepository.getIsLoadingLiveData()

    fun fetchData(id: Int) = coinDetailRepository.fetchData(id)

    override fun onCleared() {
        super.onCleared()
        coinDetailRepository.onCleared()
    }
}