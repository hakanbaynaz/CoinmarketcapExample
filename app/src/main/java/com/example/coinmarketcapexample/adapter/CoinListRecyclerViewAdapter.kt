package com.example.coinmarketcapexample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.coinmarketcapexample.R
import com.example.coinmarketcapexample.fragment.CoinListFragment
import com.example.coinmarketcapexample.model.CoinModel
import kotlinx.android.synthetic.main.coin_list_item_layout.view.*
import javax.inject.Inject

class CoinListRecyclerViewAdapter @Inject constructor() :
    ListAdapter<CoinModel, CoinListRecyclerViewAdapter.CoinListViewHolder>(DiffCallBack()) {

    private var mOnCoinClickListener: CoinListFragment.OnCoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinListViewHolder {
        return CoinListViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.coin_list_item_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CoinListViewHolder, position: Int) {
        holder.bindView(getItem(position))
    }

    fun setOnItemClickListener(onCoinClickListener: CoinListFragment.OnCoinClickListener) {
        mOnCoinClickListener = onCoinClickListener
    }

    inner class CoinListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView(coinModel: CoinModel) {
            itemView.textViewName.text = coinModel.name
            itemView.textViewSymbol.text = coinModel.symbol
            itemView.setOnClickListener {
                mOnCoinClickListener?.onCoinClick(coinModel.id)
            }
        }
    }

    class DiffCallBack : DiffUtil.ItemCallback<CoinModel>() {
        override fun areContentsTheSame(oldItem: CoinModel, newItem: CoinModel): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: CoinModel, newItem: CoinModel): Boolean {
            return oldItem.id == newItem.id
        }
    }

}