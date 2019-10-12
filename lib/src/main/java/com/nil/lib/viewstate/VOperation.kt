package com.nil.lib.viewstate

import android.graphics.drawable.Drawable
import android.view.View

import com.nil.lib.GreyDrawable

/**
 * Created by Nilesh Tak.
 */

abstract class VOperation<V : View>(internal var view: V) {
    var background: Drawable? = null


    protected open fun restore() {}

    protected fun restoreBackground() {
        this.view.setBackgroundDrawable(background)
    }

    open fun beforeStart() {
        this.background = view.background
    }

    open fun start(fadein: Boolean) {
        val greyDrawable = GreyDrawable()
        this.view.setBackgroundDrawable(greyDrawable)
        greyDrawable.isFadein = fadein
        greyDrawable.start(view )
    }

    fun stop() {
        val drawable = this.view.background
        if (drawable is GreyDrawable) {
            drawable.stop(object : GreyDrawable.Callback {
                override fun onFadeOutStarted() {
                    restore()
                }

                override fun onFadeOutFinished() {
                    restoreBackground()
                }
            })
        } else {
            restore()
        }
    }
}
