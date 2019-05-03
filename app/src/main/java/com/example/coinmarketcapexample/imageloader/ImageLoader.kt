package com.example.coinmarketcapexample.imageloader

import android.widget.ImageView

interface ImageLoader {
    fun loadImage(url: String, imageView: ImageView)
}