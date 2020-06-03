package com.example.openweathertestapp.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.openweathertestapp.R

@BindingAdapter("avatar")
fun ImageView.loadImage(imageURL: String?) {
    Glide.with(context)
            .setDefaultRequestOptions(RequestOptions().circleCrop())
            .load(imageURL)
            .placeholder(R.drawable.loading)
            .into(this)
}