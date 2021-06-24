package com.decagonhq.clads.ui.activities

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.*
import com.decagonhq.clads.R
import com.decagonhq.clads.databinding.ActivityDashboardBinding
import com.decagonhq.clads.utils.SharedPreferenceManager
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.google.android.material.bottomnavigation.BottomNavigationView

@AndroidEntryPoint
class DashboardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    private lateinit var navigationView: NavigationView
    @Inject
    lateinit var sharedPreferenceManager: SharedPreferenceManager

    private lateinit var bottomNav: BottomNavigationView
    private lateinit var toolbarProfileImage: ImageView
    private lateinit var toolbarNotificationIcon: ImageView
    private lateinit var toolbarTitle: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.dashboardActivityAppBar.myToolbar)

        window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window?.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window?.statusBarColor = resources?.getColor(R.color.white)!!
        window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

        val drawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.dashboardActivityNavigationView
        val navHeader = navView.getHeaderView(0)
        navHeader.findViewById<ImageView>(R.id.close).setOnClickListener {
            drawerLayout.closeDrawer(GravityCompat.START, true)
        }
        navHeader.findViewById<Button>(R.id.drawer_edit_profile_button).setOnClickListener {
            drawerLayout.closeDrawer(GravityCompat.START, true)
            navController.navigate(R.id.action_dashboardhomeFragment_to_editProfileFragment)
        }

        toolbarNotificationIcon = binding.dashboardActivityAppBar.toolBarNotificationIcon
        toolbarProfileImage = binding.dashboardActivityAppBar.profileImageInToolbar
        toolbarTitle = binding.dashboardActivityAppBar.toolBarTitle

        bottomNav = binding.dashboardActivityAppBar.bottomNavigationView

        // Get reference of the nav host container id
        // and set it to navController with findNavController method
        val navHostFragment = supportFragmentManager.findFragmentById(
            binding.dashboardActivityAppBar.navHostFragmentContainer.id
        ) as NavHostFragment

        navController = navHostFragment.findNavController()

        appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)

        setupActionBarWithNavController(navController, appBarConfiguration)

        bottomNav = binding.dashboardActivityAppBar.bottomNavigationView
        bottomNav.setupWithNavController(navController)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.dashboardhomeFragment -> {
                    findNavController(
                        binding.dashboardActivityAppBar.navHostFragmentContainer.id
                    ).navigate(R.id.dashboardhomeFragment)
                }
                R.id.dashboardMediaFragment -> {
                    findNavController(
                        binding.dashboardActivityAppBar.navHostFragmentContainer.id
                    ).navigate(R.id.dashboardMediaFragment)
                }
                R.id.dashboardMessagesFragment -> {
                    findNavController(
                        binding.dashboardActivityAppBar.navHostFragmentContainer.id
                    ).navigate(R.id.dashboardMessagesFragment)
                }
            }
            true
        }

        navigationView = binding.dashboardActivityNavigationView
        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.logoutFragment -> {
                    sharedPreferenceManager.clearSharedPreference()
                    this.finish()
                    return@setNavigationItemSelectedListener true
                }
                else -> {
                    return@setNavigationItemSelectedListener true
                }
            }
        }
        addOnDestinationChangedListener()
    }

    private fun addOnDestinationChangedListener() {
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.nav_host_fragment_container
        ) as NavHostFragment
        navController = navHostFragment.navController
        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.dashboardActivityAppBar
                .toolBarTitle.text = destination.label ?: getString(R.string.app_name)

            when (destination.id) {
                R.id.dashboardhomeFragment -> {
                    toolbarTitle.text = getString(R.string.tool_bar_default_title)
                    toolbarProfileImage.visibility = View.VISIBLE
                    toolbarNotificationIcon.visibility = View.VISIBLE
                    bottomNav.visibility = View.VISIBLE
                    binding.dashboardActivityAppBar.toolBarTitle.visibility = View.VISIBLE
                }
                R.id.dashboardMediaFragment -> {
                    toolbarTitle.text = destination.label
                    toolbarTitle.visibility = View.VISIBLE
                    toolbarProfileImage.visibility = View.INVISIBLE
                    toolbarNotificationIcon.visibility = View.INVISIBLE
                    bottomNav.visibility = View.VISIBLE
                }
                R.id.dashboardMessagesFragment -> {
                    toolbarTitle.text = destination.label
                    toolbarTitle.visibility = View.INVISIBLE
                    toolbarProfileImage.visibility = View.INVISIBLE
                    toolbarNotificationIcon.visibility = View.INVISIBLE
                    bottomNav.visibility = View.VISIBLE
                }
                R.id.mediaDetailFragment -> {
                    toolbarTitle.visibility = View.VISIBLE
                    toolbarProfileImage.visibility = View.INVISIBLE
                    toolbarNotificationIcon.visibility = View.GONE
                    bottomNav.visibility = View.GONE
                }
                else -> {
                    toolbarTitle.visibility = View.INVISIBLE
                    toolbarProfileImage.visibility = View.INVISIBLE
                    toolbarNotificationIcon.visibility = View.GONE
                    bottomNav.visibility = View.GONE
                }
            }
        }

        binding.dashboardActivityAppBar.myToolbar.setupWithNavController(
            navController,
            appBarConfiguration
        )

        binding.dashboardActivityNavigationView.setupWithNavController(navController)

        binding.dashboardActivityAppBar.bottomNavigationView
            .setupWithNavController(navController)
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
