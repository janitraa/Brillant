package id.ac.ui.cs.mobileprogramming.janitra.brillant

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

class MotivationActivity : AppCompatActivity() {

    private lateinit var goBtn: Button
    private lateinit var imageVw: ImageView
    private lateinit var connectivityManager: ConnectivityManager

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motivation)
        this.connectivityManager = applicationContext?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val extras = intent.extras?.getString("message")
        val extrasImg = intent.extras?.getString("photo")

        goBtn = findViewById(R.id.go)
        imageVw = findViewById(R.id.motivation)

        findViewById<TextView>(R.id.message).text = extras

        goBtn = findViewById(R.id.go)

        val mWifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        if (!mWifi!!.isConnected) {
            Picasso.get()
                .load(R.drawable.ic_noun_loading)
                .placeholder(R.drawable.ic_noun_loading)
                .resize(700, 700)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                .centerCrop()
                .into(imageVw)

            goBtn.setOnClickListener {
                Toast.makeText(
                    applicationContext,
                    "Please connect to WiFi first",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
        else {
            Picasso.get()
                .load(extrasImg)
                .placeholder(R.drawable.ic_noun_loading)
                .resize(700, 700)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                .centerCrop()
                .into(imageVw)

            goBtn.setOnClickListener {
                val context = goBtn.context
                val intent = Intent(context, MainActivity::class.java)
                context.startActivity(intent)
                finish()
            }
        }
    }
}