package com.Illarionov.art.view

import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.NavType.StringType
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.fragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.Illarionov.art.R
import com.Illarionov.art.view.ui.artist.ArtistFragment
import com.Illarionov.art.view.ui.news.NewsFragment
import com.Illarionov.art.view.ui.works.WorksFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import navigation.NavigationGraph
import navigation.NavigationGraph.Action.to_menu_artist
import navigation.NavigationGraph.Action.to_menu_newsFeed
import navigation.NavigationGraph.Action.to_menu_works
import navigation.NavigationGraph.Args.menu_artist_id
import navigation.NavigationGraph.Args.menu_newsFeed_id
import navigation.NavigationGraph.Args.menu_works_id
import navigation.NavigationGraph.Destination.artist
import navigation.NavigationGraph.Destination.newsFeed
import navigation.NavigationGraph.Destination.works

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var bottomNavView: BottomNavigationView
    private lateinit var navHostFragment: NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.menu_newsFeed,
                R.id.menu_artist,
                R.id.menu_works,
                R.id.menu_more
            )
        )

        navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController.apply {
            graph = createGraph(
                NavigationGraph.id,
                startDestination = newsFeed
            ) {
                fragment<WorksFragment>(works) {
                    label = getString(R.string.works_title)
                    action(to_menu_newsFeed) {
                        destinationId = newsFeed
                    }
                    action(to_menu_artist) {
                        destinationId = artist
                    }
                    argument(menu_works_id){
                        type = StringType
                    }
                }

                fragment<ArtistFragment>(artist) {
                    label = getString(R.string.artist_title)
                    action(to_menu_works) {
                        destinationId = works
                    }
                    action(to_menu_newsFeed) {
                        destinationId = newsFeed
                    }
                    argument(menu_artist_id) {
                        type = StringType
                    }

                }

                fragment<NewsFragment>(newsFeed) {
                    label = getString(R.string.news_title)
                    action(to_menu_works) {
                        destinationId = works
                    }
                    action(to_menu_artist) {
                        destinationId = artist
                    }
                    argument(menu_newsFeed_id) {
                        type = StringType
                    }
                }

            }
        }

        setupBottomNavMenu(navController)
        setupActionBarWithNavController(navController, appBarConfiguration)

        // Debugging tools in order to see which fragments swapped
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val dest: String = try {
                resources.getResourceName(destination.id)
            } catch (e: Resources.NotFoundException) {
                destination.id.toString()
            }

            Toast.makeText(this@MainActivity, "Navigated to $dest",
                Toast.LENGTH_SHORT).show()
            Log.d("NavigationActivity", "Navigated to $dest")
        }
    }

    override fun onSupportNavigateUp() = navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()


    private fun setupBottomNavMenu(navController: NavController) {
        bottomNavView = findViewById(R.id.bottom_navigation_view)
        bottomNavView.setupWithNavController(navController)
    }
}
