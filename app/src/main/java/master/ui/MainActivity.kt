package master.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var navHostFragment: NavHostFragment = supportFragmentManager.findFragmentById(R.id.frag_nav_bottom_navigation) as NavHostFragment
        var navController: NavController = navHostFragment.navController
        NavigationUI.setupWithNavController(navigation, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.frag_nav_bottom_navigation).navigateUp()
    }
}
