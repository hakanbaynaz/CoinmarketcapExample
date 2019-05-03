package com.example.coinmarketcapexample.adapter

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LoadMoreScrollListener(private val loadMoreListener: LoadMoreListener) : RecyclerView.OnScrollListener() {
    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)

        recyclerView.layoutManager?.let { layoutManager ->
            recyclerView.adapter?.let { adapter ->
                layoutManager is LinearLayoutManager
                layoutManager as LinearLayoutManager
                if (layoutManager.findLastCompletelyVisibleItemPosition() == adapter.itemCount - 1) {
                    loadMoreListener.onLoadMore()
                }
            }
        }
    }

    interface LoadMoreListener {
        fun onLoadMore()
    }
}