package com.example.pokedex.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.ContextMenu
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.pokedex.R

object SetupImageGlide {

    fun setImageUrl(
        imageUrl: String?,
        imageView: ImageView,
        progressBar: ProgressBar,
        context: Context
    ) {
        imageUrl.let { photoUrl ->
            Glide.with(context).load(photoUrl).listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    imageView.setImageResource(R.drawable.pikachu_surprised)
                    progressBar.visibility = View.GONE
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    progressBar.visibility = View.GONE
                    return false
                }
            }).into(imageView)
        }
    }
}