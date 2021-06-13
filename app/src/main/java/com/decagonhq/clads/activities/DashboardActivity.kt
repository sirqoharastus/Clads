package com.decagonhq.clads.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    // Binding variable
    private lateinit var dashBoardActivityBinding: ActivityDashboardBinding

    // NavController variable
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dashBoardActivityBinding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(dashBoardActivityBinding.root)

        // Get reference of the nav host container id
        // and set it to navController with findNavController method
        val navHostFragment = supportFragmentManager.findFragmentById(dashBoardActivityBinding.navHostFragmentContainer.id) as NavHostFragment
        navController = navHostFragment.findNavController()

        /** Get reference of the bottom navigation view with the binding layout,
         set setOnItemSelectedListener such that when each menu item is clicked, we set the nav controller to navigate to the desired fragment.
         Finally, return true for each action.
         * */
        val bottomNav = dashBoardActivityBinding.bottomNavigationView
        bottomNav.setupWithNavController(navController)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    findNavController(dashBoardActivityBinding.navHostFragmentContainer.id).navigate(R.id.dashboardhomeFragment)
                }
                R.id.media -> {
                    findNavController(dashBoardActivityBinding.navHostFragmentContainer.id).navigate(R.id.dashboardMediaFragment)
                }
                R.id.message -> {
                    findNavController(dashBoardActivityBinding.navHostFragmentContainer.id).navigate(R.id.dashboardMessagesFragment)
                }
            }
            true
        }
    }
}
