package master.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.activity_main.*
import master.ui.extension.disableShiftMode

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var navHostFragment: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.frag_nav_bottom_navigation) as NavHostFragment
        var navController: NavController = navHostFragment.navController
        NavigationUI.setupWithNavController(navigation, navController)
        navigation.disableShiftMode()
        // 设置actionbar
        navController.addOnNavigatedListener { controller, destination ->
            val graph = controller.graph

            val show = destination != graph.findNode(graph.startDestination)
                    && destination != graph.findNode(R.id.nav_dashboard)
                    && destination != graph.findNode(R.id.nav_notifications)
                    && destination != graph.findNode(R.id.nav_me)
            supportActionBar?.setDisplayHomeAsUpEnabled(show)// 设置actionbar是否现实返回按钮
            supportActionBar?.title = destination.label// 设置actionbar的标题
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(this, R.id.frag_nav_bottom_navigation).navigateUp()
    }
}
