package com.Illarionov.art.view.ui.artist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.Illarionov.art.R
import com.Illarionov.art.R.anim.*
import com.Illarionov.art.R.id.bottom_navigation_view
import com.Illarionov.art.R.id.menu_works
import com.Illarionov.art.R.layout.fragment_artist
import com.google.android.material.bottomnavigation.BottomNavigationView
import navigation.FragmentNavigation
import navigation.NavigationGraph.Action.to_menu_newsFeed
import navigation.NavigationGraph.Action.to_menu_works

class ArtistFragment : Fragment(), FragmentNavigation {

    private lateinit var notificationsViewModel: ArtistViewModel
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigateTo()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProviders.of(this).get(ArtistViewModel::class.java)
        return inflater.inflate(fragment_artist, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bottomNavigationView = requireActivity().findViewById(bottom_navigation_view)
    }

    override fun navigateTo() {
        val options = navOptions {
            anim {
                enter = slide_in_right
                exit = slide_out_left
                popEnter = slide_in_left
                popExit = slide_out_right
            }
        }

        bottomNavigationView.apply {
            setOnNavigationItemSelectedListener {
                when (it) {
                    this.menu.findItem(menu_works) -> {
                        findNavController().navigate(to_menu_works, null, options)
                        it.isChecked = true
                    }

                    this.menu.findItem(R.id.menu_newsFeed) -> {
                        findNavController().navigate(to_menu_newsFeed, null, options)
                        it.isChecked = true
                    }
                }
                false
            }
        }
    }
}
