package com.nil.playlikeloading

import android.graphics.Color
import android.media.Image
import android.nfc.cardemulation.CardEmulation
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import com.nil.lib.PlayLikeLoading
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Nilesh Tak.
 */

class MainActivity : AppCompatActivity() {

    lateinit var ivLogo : ImageView
    lateinit var iv1 : ImageView
    lateinit var iv2 : ImageView
    lateinit var iv3 : ImageView
    lateinit var iv4 : ImageView
    lateinit var tvName : TextView
    lateinit var cvBtn : CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ivLogo = findViewById<ImageView>(R.id.ivLogo)
        tvName = findViewById<TextView>(R.id.tvName)
        cvBtn = findViewById<CardView>(R.id.cvBtn)
        iv1 = findViewById<ImageView>(R.id.iv1)
        iv2 = findViewById<ImageView>(R.id.iv2)
        iv3 = findViewById<ImageView>(R.id.iv3)
        iv4 = findViewById<ImageView>(R.id.iv4)
        findViewById<View>(R.id.layout).setOnClickListener(View.OnClickListener { load() })
    }

    internal fun load() {
        val playLike = PlayLikeLoading.with(this)
            .withLayouts(ivLogo,tvName,cvBtn,iv1,iv2,iv3,iv4)
            .withoutLayout( )
            .borderColor("#fb0091")    // Default #BDBDBD
            .borderStroke("15f")  //Default 8f
            .borderRadius("50")       // Default 5
            .fillColor( "#ff7e67")                //  Default #FFFFFF
            .start()


        Handler().postDelayed({ playLike.stop() }, 2000)
    }
}

