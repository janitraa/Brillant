package id.ac.ui.cs.mobileprogramming.janitra.brillant

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
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
            intent.putExtra("message", "Yeay keep it up!")
            intent.putExtra("photo", "https://giantglam.com/wp-content/uploads/2019/05/Positive-Quotes-To-Make-You-Feel-Happy-1297026537910239513.jpg")
            context.startActivity(intent)
            finish()
        }

        sadBtn.setOnClickListener{
            val context = sadBtn.context
            val intent = Intent(context, MotivationActivity::class.java)
            intent.putExtra("message", "Hey, it's okay to cry. Find your happiness around you.")
            intent.putExtra("photo", "https://images.squarespace-cdn.com/content/v1/58a7541bd482e9746468c47c/1560375202427-KRQYK5I8F9WDYPULQZ0G/ke17ZwdGBToddI8pDm48kAf-OpKpNsh_OjjU8JOdDKBZw-zPPgdn4jUwVcJE1ZvWQUxwkmyExglNqGp0IvTJZUJFbgE-7XRK3dMEBRBhUpwkCFOLgzJj4yIx-vIIEbyWWRd0QUGL6lY_wBICnBy59Ye9GKQq6_hlXZJyaybXpCc/body%E2%80%9CYou+are+not+a+mistake.+You+are+not+a+problem+to+be+solved.+But+you+won%E2%80%99t+discover+this+until+you+are+willing+to+stop+banging+your+head+against+the+wall+of+shaming+and+caging+and+fearing+yourself.%E2%80%9D++%E2%80%94+Geneen+Roth+++positivity+quote+-+Geneen+Roth.png")
            context.startActivity(intent)
            finish()
        }

        fearBtn.setOnClickListener{
            val context = fearBtn.context
            val intent = Intent(context, MotivationActivity::class.java)
            intent.putExtra("message", "Hey, don't be scared. We always here for you.")
            intent.putExtra("photo", "https://cdn.lifehack.org/wp-content/uploads/2013/08/2c847ed9c1d7f1b61cf1bba2380558a6-375x380.jpg")
            context.startActivity(intent)
            finish()
        }

        angryBtn.setOnClickListener{
            val context = angryBtn.context
            val intent = Intent(context, MotivationActivity::class.java)
            intent.putExtra("message", "Hey, keep calm. Don't ruin other people's mood, okay?")
            intent.putExtra("photo", "https://www.ryrob.com/wp-content/uploads/2017/08/Hustle_Quotes_Motivation_-The-trouble-for-most-people-is-they-don%E2%80%99t--866x1024.jpg")
            context.startActivity(intent)
            finish()
        }
    }
}