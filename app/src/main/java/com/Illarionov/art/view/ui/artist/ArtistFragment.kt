package com.Illarionov.art.view.ui.artist

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.style.StyleSpan
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
import com.Illarionov.art.utils.SpanUtils
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_artist.*
import navigation.FragmentNavigation
import navigation.NavigationGraph.Action.to_menu_newsFeed
import navigation.NavigationGraph.Action.to_menu_works

private const val START_BOLD_SYMBOL = 0
private const val END_BOLD_SYMBOL = 25

class ArtistFragment : Fragment(), FragmentNavigation {

    private lateinit var notificationsViewModel: ArtistViewModel
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val spannableString = SpanUtils.setSpan(
            artist_info.text.toString(),
            StyleSpan(Typeface.BOLD),
            START_BOLD_SYMBOL,
            END_BOLD_SYMBOL,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        artist_info.text = spannableString
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
                it.isChecked = true
                when (it) {
                    this.menu.findItem(menu_works) -> {
                        findNavController().navigate(to_menu_works, null, options)
                    }

                    this.menu.findItem(R.id.menu_newsFeed) -> {
                        findNavController().navigate(to_menu_newsFeed, null, options)
                    }
                }
                false
            }
        }
    }
}
