package com.nil.lib.viewstate

import android.content.res.ColorStateList
import android.graphics.Color
import android.widget.TextView

/**
 * Created by Nilesh Tak.
 */

class TVOperation(textView: TextView) : VOperation<TextView>(textView) {
    lateinit var textColor: ColorStateList

    override fun beforeStart() {
        super.beforeStart()
        this.textColor = view.textColors

    }

    override fun restore() {
        this.view.setTextColor(textColor)
    }

    override fun start(fadein: Boolean) {
        super.start(fadein)
        view.setTextColor(Color.TRANSPARENT)
    }
}
