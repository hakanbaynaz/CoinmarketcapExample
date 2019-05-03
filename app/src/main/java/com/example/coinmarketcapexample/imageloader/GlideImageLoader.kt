package com.example.coinmarketcapexample.imageloader

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.coinmarketcapexample.dagger.ApplicationScope
import javax.inject.Inject

@ApplicationScope
class GlideImageLoader @Inject constructor() : ImageLoader {

    override fun loadImage(url: String, imageView: ImageView) {
        if (url.isNotBlank()) {
            Glide.with(imageView.context).load(url).into(imageView)
        }
    }
}