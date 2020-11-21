package id.ac.ui.cs.mobileprogramming.janitra.brillant.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import id.ac.ui.cs.mobileprogramming.janitra.brillant.R
import id.ac.ui.cs.mobileprogramming.janitra.brillant.WelcomeActivity
import id.ac.ui.cs.mobileprogramming.janitra.brillant.sharedpreferences.SharedPreferenceManager

class SplashScreenActivity : Activity() {
    private lateinit var spManager: SharedPreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed({
            spManager = SharedPreferenceManager(this)

            if (!spManager.isFirstTime()) {
                intent = Intent(this, WelcomeActivity::class.java)
                startActivity(intent)
                finish()
            }
            else {
                intent = Intent(this, EditProfileActivity::class.java)
                startActivity(intent)
            }

        }, 3000)
    }
}