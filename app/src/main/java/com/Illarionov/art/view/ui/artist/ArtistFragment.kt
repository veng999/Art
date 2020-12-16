package com.Illarionov.art.view.ui.artist

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Spannable
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.Illarionov.art.R
import com.Illarionov.art.R.id.bottom_navigation_view
import com.Illarionov.art.R.layout.fragment_artist
import com.Illarionov.art.animations.AnimationHelper
import com.Illarionov.art.utils.SpanUtils
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_artist.*
import navigation.FragmentNavigation

private const val START_BOLD_SYMBOL = 0
private const val END_BOLD_SYMBOL = 25

class ArtistFragment : Fragment(), FragmentNavigation {

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
        fab.setOnClickListener {
            val navOptions = AnimationHelper.getNavOptionsWithAnim()
            findNavController().navigate(R.id.menu_add_task, null, navOptions)
        }
        navigateTo()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(fragment_artist, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bottomNavigationView = requireActivity().findViewById(bottom_navigation_view)
    }

    override fun navigateTo() {
        val options = AnimationHelper.getNavOptionsWithAnim()

        bottomNavigationView.apply {
            setOnNavigationItemSelectedListener {
                it.isChecked = true
                findNavController().navigate(R.id.menu_works, null, options)
                false
            }
        }
    }
}
