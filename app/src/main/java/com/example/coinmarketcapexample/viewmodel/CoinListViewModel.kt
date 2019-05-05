package com.example.coinmarketcapexample.viewmodel

import androidx.lifecycle.ViewModel
import com.example.coinmarketcapexample.repository.CoinListRepository
import javax.inject.Inject

class CoinListViewModel @Inject constructor(private val coinListRepository: CoinListRepository) : ViewModel() {

    fun getLiveData() = coinListRepository.getLiveData()

    fun getErrorLiveData() = coinListRepository.getErrorLiveData()

    fun getLoadingLiveData() = coinListRepository.getIsLoadingLiveData()

    fun fetchData() = coinListRepository.fetchData()

    fun loadMore() = coinListRepository.loadMore()

    override fun onCleared() {
        super.onCleared()
        coinListRepository.onCleared()
    }
}