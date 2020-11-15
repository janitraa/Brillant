package id.ac.ui.cs.mobileprogramming.janitra.brillant

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MotivationActivity : AppCompatActivity() {

    private lateinit var message: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motivation)

        val extras = intent.extras?.getString("message")

        findViewById<TextView>(R.id.message).text = extras
    }
}