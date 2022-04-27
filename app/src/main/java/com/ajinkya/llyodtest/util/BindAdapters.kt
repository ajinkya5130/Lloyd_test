package com.ajinkya.llyodtest.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import coil.transform.RoundedCornersTransformation
import com.ajinkya.llyodtest.R


@BindingAdapter("loadImage")
fun loadImageFun(iv: ImageView, url: String) {
    iv.load(url) {
        crossfade(true)
        crossfade(500)
        error(R.drawable.circle_shape)
        placeholder(R.drawable.custom_progress_drawable)
        transformations(RoundedCornersTransformation(20f))
    }
}

@BindingAdapter("my_name")
fun TextView.setMyName(name: Int?) {
    this.text = formatDate(name!!).split(",")[0]
}

