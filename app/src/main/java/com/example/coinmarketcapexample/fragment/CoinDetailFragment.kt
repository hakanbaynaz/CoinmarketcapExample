package com.example.coinmarketcapexample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.coinmarketcapexample.R
import com.example.coinmarketcapexample.imageloader.ImageLoader
import com.example.coinmarketcapexample.viewmodel.CoinDetailViewModel
import kotlinx.android.synthetic.main.fragment_coin_detail.*
import javax.inject.Inject

class CoinDetailFragment : BaseFragment() {

    companion object {
        private const val ID_KEY = "ID_KEY"
        fun newInstance(id: Int): CoinDetailFragment {
            val bundle = Bundle()
            bundle.putInt(ID_KEY, id)
            val coinDetailFragment = CoinDetailFragment()
            coinDetailFragment.arguments = bundle
            return coinDetailFragment
        }
    }

    @Inject
    lateinit var mViewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var mImageLoader: ImageLoader

    private var mCoinId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mCoinId = arguments?.getInt(ID_KEY) ?: 0
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_coin_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.apply {
            this as AppCompatActivity
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        val coinDetailViewModel = ViewModelProviders.of(this, mViewModelFactory)[CoinDetailViewModel::class.java]

        coinDetailViewModel.getLiveData().observe(viewLifecycleOwner, Observer {
            it.status?.errorMessage?.let { errorMessage ->
                showMessage(errorMessage)
            }
            val coinDetail = it.data[mCoinId] ?: return@Observer
            mImageLoader.loadImage(coinDetail.logo, imageViewCoin)
            textViewName.text = coinDetail.name
            textViewSymbol.text = coinDetail.symbol
            textViewDescription.text = coinDetail.description
            textViewWebSite.text = coinDetail.urls.website?.get(0) ?: ""
        })

        coinDetailViewModel.getLoadingLiveData().observe(viewLifecycleOwner, Observer {
            if (it) {
                progressBar.visibility = View.VISIBLE
            } else {
                progressBar.visibility = View.GONE
            }
        })

        coinDetailViewModel.getErrorLiveData().observe(viewLifecycleOwner, Observer { apiErrorModel ->
            apiErrorModel.status.errorMessage?.let {
                showMessage(it)
            }
        })

        coinDetailViewModel.fetchData(mCoinId)
    }
}