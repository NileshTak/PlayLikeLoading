package com.nil.lib.viewstate

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.widget.ImageView

/**
 * Created by Nilesh Tak.
 */
class IVOperation(imageView: ImageView) : VOperation<ImageView>(imageView) {
    lateinit var source: Drawable

    override fun beforeStart() {
        super.beforeStart()
        this.source = view.drawable
        view.setImageDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    override fun restore() {
        this.view.setImageDrawable(source)
    }
}
