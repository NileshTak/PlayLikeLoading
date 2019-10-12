package com.nil.lib

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.*
import android.graphics.drawable.Drawable


import android.view.View
import android.view.animation.AccelerateInterpolator
import android.view.animation.LinearInterpolator
import androidx.core.view.ViewCompat.LAYER_TYPE_SOFTWARE
import androidx.core.view.ViewCompat.setLayerType

import java.lang.ref.WeakReference
import android.content.Context as n

/**
 * Created by Nilesh Tak.
 */

class GreyDrawable : Drawable() {

    var isFadein = false

    private var grayColor = DEFAULT_GREY
    lateinit var valueAnimator: ValueAnimator
    private var paint: Paint
    private var strokePaint: Paint

    lateinit var viewWeakReference: WeakReference<View>

    init {
        paint = Paint()
        strokePaint = Paint()
    }



    override fun draw(canvas: Canvas) {
        paint.color = grayColor

        //stroke
        strokePaint.style = Paint.Style.STROKE
        strokePaint.color = Color.parseColor(PlayLikeLoading.with(c).colorB)
        strokePaint.strokeWidth = PlayLikeLoading.with(c).stroke!!.toFloat()

        val offset = 5
        val cornersRadius = PlayLikeLoading.with(c).corner!!.toInt()

        val rectF = RectF(
            offset.toFloat(), // left
            offset.toFloat(), // top
            (canvas.width - offset).toFloat(), // right
            (canvas.height - offset).toFloat() // bottom
        )

        canvas.drawRoundRect(  rectF, // rect
            cornersRadius.toFloat(), // rx
            cornersRadius.toFloat(), // ry
            paint)

        canvas.drawRoundRect(
            rectF, // rect
            cornersRadius.toFloat(), // rx
            cornersRadius.toFloat(), // ry
            strokePaint
        )

        //  canvas.drawRect(canvas.getClipBounds(), strokePaint);


    }

    override fun setAlpha(i: Int) {

    }

    override fun setColorFilter(colorFilter: ColorFilter?) {

    }


    override fun getOpacity(): Int {
        return 255
    }

    fun start(view: View ) {
        viewWeakReference = WeakReference(view)

        val defaultGrey = DEFAULT_GREY
        val darkerGrey = DARKER_GREY

        valueAnimator = ValueAnimator.ofInt(defaultGrey, darkerGrey)
        valueAnimator.repeatCount = ObjectAnimator.INFINITE
        valueAnimator.duration = DURATION.toLong()
        valueAnimator.setEvaluator(ArgbEvaluator())
        valueAnimator.repeatMode = ValueAnimator.REVERSE
        valueAnimator.interpolator = LinearInterpolator()
        valueAnimator.addUpdateListener { valueAnimator ->
            grayColor = valueAnimator.animatedValue as Int

            val v = viewWeakReference.get()
            v?.invalidate()
        }
        valueAnimator.start()
    }

    fun cancel() {
        valueAnimator.cancel()
    }

    fun stop(callback: Callback?) {
        valueAnimator.cancel()

        if (isFadein) {
            stopFadeIn(callback)
        } else {
            stopGray(callback)
        }
    }

    fun stopFadeIn(callback: Callback?) {
        if (callback != null) {
            val v = viewWeakReference.get()
            if (v != null) {
                val alphaAnimator = ObjectAnimator.ofFloat(v, View.ALPHA, 0f).setDuration(400)
                alphaAnimator.addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        callback.onFadeOutStarted()

                        callback.onFadeOutFinished()
                        val v = viewWeakReference.get()
                        if (v != null) {
                            ObjectAnimator.ofFloat(v, View.ALPHA, 1f).start()
                        }
                    }

                    override fun onAnimationCancel(animation: Animator) {
                        onAnimationEnd(animation)
                    }
                })
                alphaAnimator.start()
            }
        }
    }

    fun stopGray(callback: Callback?) {

        val emptyColor =
            Color.argb(0, Color.red(grayColor), Color.green(grayColor), Color.blue(grayColor))
        valueAnimator = ValueAnimator.ofInt(grayColor, emptyColor)
        valueAnimator.duration = STOP_DURATION.toLong()
        valueAnimator.setEvaluator(ArgbEvaluator())
        valueAnimator.interpolator = AccelerateInterpolator()
        valueAnimator.addUpdateListener { valueAnimator ->
            grayColor = valueAnimator.animatedValue as Int

            val v = viewWeakReference.get()
            v?.invalidate()
        }

        valueAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                if (callback != null) {
                    callback.onFadeOutFinished()
                    val v = viewWeakReference.get()
                    if (v != null) {
                        ObjectAnimator.ofFloat(v, View.ALPHA, 1f).start()
                    }
                }
            }

            override fun onAnimationCancel(animation: Animator) {
                super.onAnimationCancel(animation)
                callback?.onFadeOutFinished()
            }
        })
        valueAnimator.startDelay = 50
        valueAnimator.duration = 400
        valueAnimator.start()

        callback?.onFadeOutStarted()
    }

    interface Callback {
        fun onFadeOutStarted()

        fun onFadeOutFinished()
    }

    companion object {
        val DEFAULT_GREY = Color.parseColor(PlayLikeLoading.with(c).fColor)
        val DARKER_GREY = Color.argb(160, 255, 255, 255)

        val DURATION = 750
        val STOP_DURATION = 200
    }
}
