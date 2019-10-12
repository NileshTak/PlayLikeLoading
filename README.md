# PlayLikeLoading 
 
 PlayLikeLoading can be used to add **Play Store like loading Animation** (like the one used at Playstore or at LinkedIn) to your Android application. Beside memory efficiency even animating a big layout, you can customize the loading animation view and look of the animation. Check out the wiki for attributes!
 
 Instead of using progress dialogs now you can use this **Play Store like Loading Animation**
 
 ![](20191012_154113.gif)         ![](2.gif)      ![](3.gif)
 ![](4.gif)
 
 # Download and usage
 
 Get the latest artifact via gradle
 
  * Add Dependency :
  
 > implementation 'com.github.NileshTak:PlayLikeLoading:0.1.0'
 
  * Add Maven URL :
  
 > maven { url 'https://jitpack.io' }
 
 * Create the layout on which you want to apply the effect
   
 * Last, but not least you have to start it from your Kotlin code 
 
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
                .borderColor("#fb0091")
                .borderStroke("15f")
                .borderRadius("50")
                .fillColor( "#ff7e67")
                .start()
    
    
            Handler().postDelayed({ playLike.stop() }, 2000)
    }
}


 
 * To Change Border Color
 
 >  .borderColor("HashCode of Your Border Color")    // Default #BDBDBD
 
 * To Change Border Stroke
 
 >  .borderStroke("Border Stroke Value in Float")  //Default 8f
 
 * To Change Border Radius
 
 > .borderRadius("Border Value in Integer")       // Default 5
 
 * To Change Filled Color
 
 > .fillColor( "HashCode of Your Color")      //  Default White
 
 
 
 * Inspired By :
 https://github.com/florent37/FiftyShadesOf
 
 
 


 
 
