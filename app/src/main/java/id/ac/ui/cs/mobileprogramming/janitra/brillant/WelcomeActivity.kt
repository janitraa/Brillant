package id.ac.ui.cs.mobileprogramming.janitra.brillant

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

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
            context.startActivity(intent)        }
    }
}