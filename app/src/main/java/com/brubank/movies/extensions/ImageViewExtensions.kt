package com.brubank.movies.extensions

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImageFromUrl(
    url: String,
    context: Context
){
    if (url.contains(".gif"))  {
        Glide.with(context)
            .asGif()
            .load(url)
            .into(this)
    } else {
        Glide.with(context)
            .load(url)
            .into(this)
    }

}