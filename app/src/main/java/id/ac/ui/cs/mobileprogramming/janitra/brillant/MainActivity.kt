package id.ac.ui.cs.mobileprogramming.janitra.brillant

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import id.ac.ui.cs.mobileprogramming.janitra.brillant.ui.ListDeadlineFragment
import id.ac.ui.cs.mobileprogramming.janitra.brillant.ui.ProfileFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val navbar: BottomNavigationView = findViewById(R.id.navigation)
        navbar.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    navigateToOtherMenu(ListDeadlineFragment())
                }

                R.id.navigation_deadline -> {
                    navigateToOtherMenu(ListDeadlineFragment())
                }

                R.id.navigation_profile -> {
                    navigateToOtherMenu(ProfileFragment())
                }

                else -> {
                    true
                }
            }
        }
    }

    fun navigateToOtherMenu(givenFragment: Fragment): Boolean {
        val fragmentTransaction: FragmentTransaction = this.supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_home, givenFragment, givenFragment.toString())
        fragmentTransaction.commit()
        return true
    }

    override fun onConfigurationChanged(newConfig: Configuration) {

        val orientation = resources.configuration.orientation

        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            val intent = Intent(this, MainActivityLandscape::class.java)
            startActivity(intent)
        }
        super.onConfigurationChanged(newConfig)
    }
}
