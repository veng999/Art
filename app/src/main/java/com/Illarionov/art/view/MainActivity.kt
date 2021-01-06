package com.Illarionov.art.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.Illarionov.art.R
import com.Illarionov.art.animations.AnimationHelper
import com.google.android.material.bottomnavigation.BottomNavigationView
import navigation.FragmentNavigation

class MainActivity : AppCompatActivity(), FragmentNavigation {

    private val navController by lazy(LazyThreadSafetyMode.NONE) {
        (supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment)
            .navController
    }
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var bottomNavView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                /*R.id.menu_newsFeed,*/
                R.id.menu_artist,
                R.id.menu_works
                /*R.id.menu_more*/
            )
        )

        setupBottomNavMenu(navController)
        setBottomNavigationListener()
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp() =
        navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()


    private fun setupBottomNavMenu(navController: NavController) {
        bottomNavView = findViewById(R.id.bottom_navigation_view)
        bottomNavView.setupWithNavController(navController)
    }

    override fun setBottomNavigationListener() {
        val options = AnimationHelper.getNavOptionsWithAnim()

        bottomNavView.apply {
            setOnNavigationItemSelectedListener { menuItem ->
                menuItem.isChecked = true
                when (menuItem) {
                    menu.findItem(R.id.menu_works) ->
                        navController.navigate(R.id.menu_works, null, options)

                    menu.findItem(R.id.menu_artist) ->
                        navController.navigate(R.id.action_menu_works_to_menu_artist, null, options)
                }
                false
            }
        }
    }
}
