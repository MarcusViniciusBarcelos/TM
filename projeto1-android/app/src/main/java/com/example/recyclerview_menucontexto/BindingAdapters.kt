package com.example.recyclerview_menucontexto

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapters {
    @BindingAdapter("loadImg")
    @JvmStatic
    fun loadImg(view: ImageView, url: String) {
        Glide.with(view.context)
            .load(url)
            .centerCrop()
            .into(view)
    }
}