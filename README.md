# PlayLikeLoading 
 
 PlayLikeLoading can be used to add Play Store like loading Animation (like the one used at Playstore or at LinkedIn) to your Android application. Beside memory efficiency even animating a big layout, you can customize the loading animation view and look of the animation. Check out the wiki for attributes!
 
 ![](20191012_154113.gif)
 
 # Download and usage
 
 Get the latest artifact via gradle
 
  * Add Dependency :
 > implementation 'com.github.NileshTak:PlayLikeLoading:0.1.0'
 
  * Add Maven URL :
 > maven { url 'https://jitpack.io' }
 
 Create the layout on which you want to apply the effect
 
 > <?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:paddingBottom="@dimen/activity_vertical_margin"
    android:id="@+id/layout"
    android:paddingLeft="@dimen/activity_horizontal_margin"
    android:paddingRight="@dimen/activity_horizontal_margin"
    android:paddingTop="@dimen/activity_vertical_margin"
    android:orientation="vertical"
    tools:context=".MainActivity">

    <RelativeLayout
        android:padding="4dp"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" >

        <ImageView
            android:id="@+id/ivLogo"
            android:src="@drawable/a"
            android:layout_width="80dp"
            android:layout_height="80dp"/>

        <TextView
            android:id="@+id/tvName"
            android:layout_toRightOf="@+id/ivLogo"
            android:layout_width="match_parent"
            android:textSize="22dp"
            android:layout_marginTop="4dp"
            android:layout_marginLeft="5dp"
            android:textStyle="bold"
            android:text="Play Like Loading Demo App"
            android:textColor="@android:color/black"
            android:layout_height="wrap_content"/>

    </RelativeLayout>

    <androidx.cardview.widget.CardView
        android:id="@+id/cvBtn"
        app:cardCornerRadius="5dp"
        android:layout_marginTop="5dp"

        android:layout_width="match_parent"
        android:layout_height="35dp">

        <Button
            android:id="@+id/btn"
            android:layout_width="match_parent"
            android:background="@android:color/holo_green_dark"
            android:text="Install"
            android:layout_height="match_parent"/>


    </androidx.cardview.widget.CardView>

    <RelativeLayout

        android:layout_marginTop="5dp"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" >


        <ImageView
            android:id="@+id/iv1"
            android:layout_margin ="5dp"

            android:src="@drawable/iv1"
            android:scaleType="fitXY"
            android:layout_width="90dp"
            android:layout_height="160dp"/>


        <ImageView
            android:id="@+id/iv2"
            android:layout_toRightOf="@+id/iv1"
            android:src="@drawable/iv2"
            android:layout_margin ="5dp"
            android:layout_width="90dp"
            android:scaleType="fitXY"
            android:layout_height="160dp"/>


        <ImageView
            android:id="@+id/iv3"
            android:layout_toRightOf="@+id/iv2"
            android:src="@drawable/iv3"
            android:layout_margin ="5dp"

            android:scaleType="fitXY"
            android:layout_width="90dp"
            android:layout_height="160dp"/>

        <ImageView
            android:id="@+id/iv4"
            android:layout_toRightOf="@+id/iv3"
            android:src="@drawable/iv1"
            android:layout_margin ="5dp"

            android:scaleType="fitXY"
            android:layout_width="90dp"
            android:layout_height="160dp"/>


    </RelativeLayout>


</LinearLayout>

Last, but not least you have to start it from your Kotlin code 

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
            .borderColor("")    // Default #BDBDBD
            .borderStroke("15f")  //Default 8f
            .borderRadius("")       // Default 5
            .fillColor( "")                //  Default #FFFFFF
            .start()


        Handler().postDelayed({ playLike.stop() }, 2000)
    }
}


 
 
