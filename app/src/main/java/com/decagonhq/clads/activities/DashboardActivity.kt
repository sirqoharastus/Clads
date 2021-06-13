package com.decagonhq.clads.activities

import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    private lateinit var dashBoardActivityBinding: ActivityDashboardBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dashBoardActivityBinding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(dashBoardActivityBinding.root)
        setUpUI()
    }

    private fun setUpUI() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->

            when {

                (destination.id != R.id.dashboardhomeFragment && destination.id != R.id.dashboardMediaFragment && destination.id != R.id.dashboardMessagesFragment) -> {
                    dashBoardActivityBinding.dashboardActivityAppBar.profileImageInToolbar.visibility = View.GONE
                    dashBoardActivityBinding.dashboardActivityAppBar.toolBarNotificationIcon.visibility = View.GONE
                    dashBoardActivityBinding.dashboardActivityAppBar.bottomNavigationView.visibility = View.GONE
                    dashBoardActivityBinding.dashboardActivityAppBar.toolBarTitle.visibility = View.GONE
                }
                (destination.id == R.id.dashboardMediaFragment || destination.id == R.id.dashboardMessagesFragment) -> {
                    dashBoardActivityBinding.dashboardActivityAppBar.profileImageInToolbar.visibility = View.GONE
                    dashBoardActivityBinding.dashboardActivityAppBar.toolBarNotificationIcon.visibility = View.GONE
                    dashBoardActivityBinding.dashboardActivityAppBar.toolBarTitle.visibility = View.GONE
                    dashBoardActivityBinding.dashboardActivityAppBar.bottomNavigationView.visibility = View.VISIBLE
                }
                else -> {
                    dashBoardActivityBinding.dashboardActivityAppBar.bottomNavigationView.visibility = View.VISIBLE
                    dashBoardActivityBinding.dashboardActivityAppBar.profileImageInToolbar.visibility = View.VISIBLE
                    dashBoardActivityBinding.dashboardActivityAppBar.toolBarTitle.visibility = View.VISIBLE
                    dashBoardActivityBinding.dashboardActivityAppBar.toolBarNotificationIcon.visibility = View.VISIBLE
                }
            }
        }

        val drawerLayout = dashBoardActivityBinding.drawerLayout
        val navHeader = dashBoardActivityBinding.dashboardActivityNavigationView.getHeaderView(0)
        navHeader.findViewById<ImageView>(R.id.close)
            .setOnClickListener {
                drawerLayout.closeDrawer(Gravity.LEFT, true)
            }

        navHeader.findViewById<Button>(R.id.drawer_edit_profile_button).setOnClickListener {
            drawerLayout.closeDrawer(Gravity.LEFT, true)
            navController.navigate(R.id.action_dashboardhomeFragment_to_editProfileFragment)
        }
        appBarConfiguration = AppBarConfiguration(
            navController.graph, drawerLayout
        )
        dashBoardActivityBinding.dashboardActivityAppBar.myToolbar.setupWithNavController(
            navController,
            appBarConfiguration
        )

        setSupportActionBar(dashBoardActivityBinding.dashboardActivityAppBar.myToolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
        dashBoardActivityBinding.dashboardActivityNavigationView.setupWithNavController(navController)
        dashBoardActivityBinding.dashboardActivityAppBar.bottomNavigationView.setupWithNavController(
            navController
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (R.id.logoutFragment == item.itemId) {
            return false
        }
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {

        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
