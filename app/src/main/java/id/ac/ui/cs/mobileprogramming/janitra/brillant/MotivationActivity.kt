package id.ac.ui.cs.mobileprogramming.janitra.brillant

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*
import java.io.InputStream
import java.net.URL

class MotivationActivity : AppCompatActivity() {

    private lateinit var goBtn: Button
    private lateinit var imageVw: ImageView

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    var thread = newSingleThreadContext("test") as CoroutineDispatcher

    @kotlinx.coroutines.ObsoleteCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motivation)

        val extras = intent.extras?.getString("message")
        val extrasImg = intent.extras?.getString("photo")

        goBtn = findViewById(R.id.go)
        imageVw = findViewById(R.id.motivation)

//        triggerANR()
        Picasso.get()
            .load(extrasImg)
            .placeholder(R.drawable.ic_noun_loading)
            .resize(64, 64)
            .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
            .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
            .centerCrop()
            .into(imageVw)

//        GlobalScope.launch {
//            withContext(Dispatchers.Main){
//                var bitmap : Bitmap
//                withContext(Dispatchers.IO){
//                    bitmap = BitmapFactory.decodeStream(URL(extrasImg).content as InputStream)
//                }
//                imageVw.setImageBitmap(bitmap)
//            }
//
//        }

//        val bitmap = BitmapFactory.decodeStream(URL(extrasImg).content as InputStream)

        Toast.makeText(this, extrasImg, Toast.LENGTH_SHORT).show()

        findViewById<TextView>(R.id.message).text = extras

        goBtn = findViewById(R.id.go)

        goBtn.setOnClickListener{
            val context = goBtn.context
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

//    @kotlinx.coroutines.ObsoleteCoroutinesApi
//    fun triggerANR() = GlobalScope.launch(thread) {
//        while (true) {
//            Thread.sleep(1000000)
//            break
//        }
//    }
}