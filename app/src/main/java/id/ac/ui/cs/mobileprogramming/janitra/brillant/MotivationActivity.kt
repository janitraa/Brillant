package id.ac.ui.cs.mobileprogramming.janitra.brillant

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MotivationActivity : AppCompatActivity() {

    private lateinit var goBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motivation)

        val extras = intent.extras?.getString("message")

        findViewById<TextView>(R.id.message).text = extras

        goBtn = findViewById(R.id.go)

        goBtn.setOnClickListener{
            val context = goBtn.context
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }

    }
}