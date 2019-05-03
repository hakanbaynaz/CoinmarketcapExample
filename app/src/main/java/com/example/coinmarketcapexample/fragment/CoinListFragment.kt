package com.example.coinmarketcapexample.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coinmarketcapexample.R
import com.example.coinmarketcapexample.adapter.CoinListRecyclerViewAdapter
import com.example.coinmarketcapexample.adapter.LoadMoreScrollListener
import com.example.coinmarketcapexample.viewmodel.CoinListViewModel
import kotlinx.android.synthetic.main.fragment_coin_list.*
import javax.inject.Inject


class CoinListFragment : BaseFragment(), LoadMoreScrollListener.LoadMoreListener {

    interface OnCoinClickListener {
        fun onCoinClick(coinId: Int)
    }

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var mCoinListRecyclerViewAdapter: CoinListRecyclerViewAdapter

    private lateinit var mCoinListViewModel: CoinListViewModel

    private lateinit var mLoadMoreScrollListener: LoadMoreScrollListener

    private lateinit var mOnCoinClickListener: OnCoinClickListener


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnCoinClickListener) {
            mOnCoinClickListener = context
        } else {
            throw ClassCastException(activity?.toString() + " must implement OnCoinClickListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mLoadMoreScrollListener = LoadMoreScrollListener(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_coin_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mCoinListRecyclerViewAdapter.setOnItemClickListener(mOnCoinClickListener)

        coinListRecyclerView.adapter = mCoinListRecyclerViewAdapter
        coinListRecyclerView.layoutManager = LinearLayoutManager(context)
        mCoinListViewModel = ViewModelProviders.of(this, mViewModelFactory)[CoinListViewModel::class.java]
        mCoinListViewModel.getLiveData().observe(viewLifecycleOwner, Observer {
            it.status?.errorMessage?.let { errorMessage ->
                showMessage(errorMessage)
            }
            mCoinListRecyclerViewAdapter.submitList(it.data)
            mCoinListRecyclerViewAdapter.notifyDataSetChanged()
        })

        mCoinListViewModel.getErrorLiveData().observe(viewLifecycleOwner, Observer { apiErrorModel ->
            apiErrorModel.status.errorMessage?.let {
                showMessage(it)
            }
        })

        mCoinListViewModel.getLoadingLiveData().observe(viewLifecycleOwner, Observer { isLoading ->
            if (isLoading) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        })
        mCoinListViewModel.fetchData()
    }

    override fun onStart() {
        super.onStart()
        coinListRecyclerView.addOnScrollListener(mLoadMoreScrollListener)
    }

    override fun onStop() {
        super.onStop()
        coinListRecyclerView.removeOnScrollListener(mLoadMoreScrollListener)
    }

    override fun onLoadMore() {
        mCoinListViewModel.loadMore()
    }

}