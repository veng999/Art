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
import com.Illarionov.art.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import navigation.FragmentNavigation

class MainActivity : AppCompatActivity(), FragmentNavigation {

    private val navController by lazy(LazyThreadSafetyMode.NONE) {
        (supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment)
            .navController
    }
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var bottomNavView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
        bottomNavView = binding.bottomNavigationView
        bottomNavView.setupWithNavController(navController)
    }

    override fun setBottomNavigationListener() {
        bottomNavView.apply {
            setOnNavigationItemSelectedListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.menu_works -> navigate(R.id.menu_works)
                    R.id.menu_artist -> navigate(R.id.menu_artist)
                    R.id.menu_add_task -> navigate(R.id.menu_add_task)
                    R.id.menu_tasks_list -> navigate(R.id.menu_tasks_list)
                }
                false
            }
        }
    }

    private fun navigate(action: Int) {
        val options = AnimationHelper.getNavOptionsWithAnim()
        navController.navigate(action, null, options)
    }
}
