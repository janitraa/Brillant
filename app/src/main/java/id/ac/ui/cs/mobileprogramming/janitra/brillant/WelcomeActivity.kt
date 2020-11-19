package id.ac.ui.cs.mobileprogramming.janitra.brillant

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : AppCompatActivity() {

    lateinit var happyBtn: ImageButton
    lateinit var sadBtn: ImageButton
    lateinit var fearBtn: ImageButton
    lateinit var angryBtn: ImageButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        happyBtn = findViewById(R.id.happy)
        sadBtn = findViewById(R.id.sad)
        fearBtn = findViewById(R.id.fear)
        angryBtn = findViewById(R.id.angry)

        happyBtn.setOnClickListener{
            val context = happyBtn.context
            val intent = Intent(context, MotivationActivity::class.java)
            intent.putExtra("message", "Yeay keep that up!")
            triggerANR()
            Picasso.get()
                .load("https://www.pngkey.com/png/full/352-3525258_dont-symbol-clipart-best-casino.png")
                .placeholder(R.drawable.ic_noun_loading)
                .resize(64, 64)
                .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
                .networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE)
                .centerCrop()
                .into(imageView)
            context.startActivity(intent)
        }

        sadBtn.setOnClickListener{
            val context = sadBtn.context
            val intent = Intent(context, MotivationActivity::class.java)
            intent.putExtra("message", "Hey, it's okay to cry. Find your happiness around you.")
            context.startActivity(intent)
        }

        fearBtn.setOnClickListener{
            val context = fearBtn.context
            val intent = Intent(context, MotivationActivity::class.java)
            intent.putExtra("message", "Hey, don't be scared. We always here for you.")
            context.startActivity(intent)
        }

        angryBtn.setOnClickListener{
            val context = angryBtn.context
            val intent = Intent(context, MotivationActivity::class.java)
            intent.putExtra("message", "Hey, keep calm. Don't also ruin other people's mood.")
            context.startActivity(intent)
        }
    }
}