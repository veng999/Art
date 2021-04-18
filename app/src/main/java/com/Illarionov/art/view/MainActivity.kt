package com.Illarionov.art.view

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.Illarionov.art.R
import com.Illarionov.art.databinding.ActivityMainBinding
import com.Illarionov.art.utils.AnimationHelper
import navigation.FragmentNavigation

class MainActivity : AppCompatActivity(), FragmentNavigation {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupNavView()
        setupNavViewListeners()
        setupBottomNavMenu(navController)
        setBottomNavigationListener()
        setStatusBarColor()
        setActionBarColor()
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp() =
        navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()

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

    private val navController by lazy {
        (supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment)
            .navController
    }

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val appBarConfiguration by lazy {
        AppBarConfiguration(
            setOf(
                /*R.id.menu_newsFeed,*/
                R.id.menu_artist,
                R.id.menu_works
                /*R.id.menu_more*/
            ), binding.drawerLayout
        )
    }

    private val bottomNavView by lazy {
        binding.bottomNavigationView
    }

    private fun setActionBarColor() {
        val colorDrawable = ColorDrawable(resources.getColor(R.color.holo_blue_dark))
        supportActionBar?.setBackgroundDrawable(colorDrawable)
    }

    private fun setStatusBarColor() {
        window.statusBarColor = resources.getColor(R.color.holo_blue_dark)
    }

    private fun setupNavView() = binding.navView.setupWithNavController(navController)

    private fun setupBottomNavMenu(navController: NavController) =
        bottomNavView.setupWithNavController(navController)

    private fun setupNavViewListeners() {
        binding.navView.apply {
            setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.menu_feedback -> {
                        navigate(R.id.menu_feedback)
                        closeDrawerLayout()
                    }
                }
                false
            }
        }
    }

    private fun closeDrawerLayout() {
        with(binding.drawerLayout) {
            setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        }
    }

    private fun navigate(action: Int) {
        val options = AnimationHelper.getNavOptionsWithAnim()
        navController.navigate(action, null, options)
    }
}
