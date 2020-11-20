package id.ac.ui.cs.mobileprogramming.janitra.brillant

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import id.ac.ui.cs.mobileprogramming.janitra.brillant.ui.ListDeadlineFragment

class MainActivityLandscape : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_landscape)

        val navbar: BottomNavigationView = findViewById(R.id.navigation)

        navbar.setOnNavigationItemSelectedListener { menuItem ->

            val parentFragment: LinearLayout = findViewById(R.id.fragment_container)

            when (menuItem.itemId) {
                R.id.navigation_home -> {
                    parentFragment.weightSum = 2.toFloat()
                    navigateToOtherMenu(ListDeadlineFragment())
                }

                R.id.navigation_deadline -> {
                    parentFragment.weightSum = 1.toFloat()
                    navigateToOtherMenu(ListDeadlineFragment())
                }

                R.id.navigation_profile -> {
                    parentFragment.weightSum = 1.toFloat()
                    navigateToOtherMenu(ListDeadlineFragment())
                }

                else -> {
                    true
                }
            }
        }
    }

    override fun onConfigurationChanged(newConfig: Configuration) {

        val orientation = resources.configuration.orientation

        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        super.onConfigurationChanged(newConfig)
    }

    fun navigateToOtherMenu(givenFragment: Fragment): Boolean {
        val fragmentTransaction: FragmentTransaction = this.supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_home, givenFragment, givenFragment.toString())
        fragmentTransaction.commit()
        return true
    }
}