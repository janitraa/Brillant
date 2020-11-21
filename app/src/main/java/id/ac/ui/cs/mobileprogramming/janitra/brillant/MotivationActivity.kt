package id.ac.ui.cs.mobileprogramming.janitra.brillant

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

class MotivationActivity : AppCompatActivity() {

    private lateinit var goBtn: Button
    private lateinit var imageVw: ImageView

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motivation)

        val extras = intent.extras?.getString("message")
        val extrasImg = intent.extras?.getString("photo")

        goBtn = findViewById(R.id.go)
        imageVw = findViewById(R.id.motivation)

        Picasso.get()
            .load(extrasImg)
            .placeholder(R.drawable.ic_noun_loading)
            .resize(200, 200)
            .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
            .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
            .centerCrop()
            .into(imageVw)

        findViewById<TextView>(R.id.message).text = extras

        goBtn = findViewById(R.id.go)

        goBtn.setOnClickListener{
            val context = goBtn.context
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
            finish()
        }
    }
}