package com.juanvargas.konfioprueba.util.binding_adapters

import android.graphics.drawable.Drawable
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView

@BindingAdapter("imageUrl", "placeholder", requireAll = false)
fun ShapeableImageView.setImage(url: String?, placeHolder: Drawable?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(context)
            .load(url)
            .placeholder(placeHolder)
            .into(this)
    } else {
        setImageDrawable(placeHolder)
    }
}