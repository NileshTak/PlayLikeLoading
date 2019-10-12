package com.nil.lib

import android.app.Activity
import android.content.Context
import android.preference.PreferenceManager
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView


import com.nil.lib.viewstate.IVOperation
import com.nil.lib.viewstate.TVOperation
import com.nil.lib.viewstate.VOperation

import java.util.HashMap

/**
 * Created by Nilesh Tak.
 */

lateinit var c : Context

class PlayLikeLoading(val context: Context) {

    private val viewsState: HashMap<View, VOperation<*>>


    private var fadein = true


    val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    var colorB : String? =   sharedPreferences.getString("colorCode", "#BDBDBD")
    var stroke : String? = sharedPreferences.getString("borderStroke","#8f")
    var corner : String? = sharedPreferences.getString("borderRadius","5")
    var fColor : String? = sharedPreferences.getString("fillColor","#FFFFFF")

    private var started: Boolean = false

    init {
        this.viewsState = HashMap()

        c = context

     }

    fun withLayouts(vararg views: View): PlayLikeLoading {
        for (view in views)
            add(view)
        return this
    }


//    fun withLayouts(vararg viewsId: Int): PlayLikeLoading {
//        if (context is Activity) {
//            for (view in viewsId) {
//                add(context.findViewById(view))
//
//            }
//        }
//        return this
//    }



    private fun fadein(fadein: Boolean): PlayLikeLoading {
        this.fadein = fadein
        return this
    }

    private fun add(view: View) {
        if (view is TextView) {
            viewsState[view] = TVOperation(view)
        } else if (view is ImageView) {
            viewsState[view] = IVOperation(view)
        } else if (view is ViewGroup) {
            val count = view.childCount
            for (i in 0 until count) {
                val child = view.getChildAt(i)
                add(child)
            }
        }
    }


    fun withoutLayout(vararg views: View): PlayLikeLoading {
        for (view in views) {
            this.viewsState.remove(view)
        }
        return this
    }

    fun start(): PlayLikeLoading {
        if (!started) {
            //prepare for starting
            for (viewState in viewsState.values) {
                viewState.beforeStart()
            }
            started = true
            //start
            for (viewState in viewsState.values) {
                viewState.start(fadein)
            }
        }
        return this
    }




    fun borderColor(col : String) : PlayLikeLoading
    {
        if (col == "")
        {
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            val sharedPrefEditor = sharedPreferences.edit()
            sharedPrefEditor.putString("colorCode", "#BDBDBD")
            sharedPrefEditor.apply()
        }
        else{

            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            val sharedPrefEditor = sharedPreferences.edit()
            sharedPrefEditor.putString("colorCode", col)
            sharedPrefEditor.apply()

        }

        return this
    }


    fun borderStroke(stroke : String) : PlayLikeLoading
    {
        if (stroke == "")
        {
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            val sharedPrefEditor = sharedPreferences.edit()
            sharedPrefEditor.putString("borderStroke", "8f")
            sharedPrefEditor.apply()
        }
        else{

            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            val sharedPrefEditor = sharedPreferences.edit()
            sharedPrefEditor.putString("borderStroke", stroke)
            sharedPrefEditor.apply()

        }

        return this
    }

    fun borderRadius(radius : String) : PlayLikeLoading
    {
        if (radius == "")
        {
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            val sharedPrefEditor = sharedPreferences.edit()
            sharedPrefEditor.putString("borderRadius", "5")
            sharedPrefEditor.apply()
        }
        else{

            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            val sharedPrefEditor = sharedPreferences.edit()
            sharedPrefEditor.putString("borderRadius", radius)
            sharedPrefEditor.apply()

        }

        return this
    }

    fun fillColor(color : String) : PlayLikeLoading
    {
        if (color == "")
        {
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            val sharedPrefEditor = sharedPreferences.edit()
            sharedPrefEditor.putString("fillColor", "#FFFFFF")
            sharedPrefEditor.apply()
        }
        else{

            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            val sharedPrefEditor = sharedPreferences.edit()
            sharedPrefEditor.putString("fillColor", color)
            sharedPrefEditor.apply()

        }

        return this
    }


    fun stop(): PlayLikeLoading {
        if (started) {
            for (viewState in viewsState.values) {
                viewState.stop()
            }
            started = false
        }
        return this
    }

    companion object {

        fun with(context: Context): PlayLikeLoading {
            return PlayLikeLoading(context)
        }
    }

}
