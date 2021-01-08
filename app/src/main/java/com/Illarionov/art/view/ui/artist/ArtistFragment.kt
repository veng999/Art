package com.Illarionov.art.view.ui.artist

import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.Illarionov.art.App
import com.Illarionov.art.R
import com.Illarionov.art.animations.AnimationHelper
import com.Illarionov.art.databinding.FragmentArtistBinding
import com.Illarionov.art.di.MainComponent
import com.Illarionov.art.utils.SpanUtils

private const val START_BOLD_SYMBOL = 0
private const val END_BOLD_SYMBOL = 25

class ArtistFragment : Fragment() {

    private var binding: FragmentArtistBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val spannableString = SpanUtils.setSpan(
            binding?.artistInfo?.text.toString(),
            StyleSpan(Typeface.BOLD),
            START_BOLD_SYMBOL,
            END_BOLD_SYMBOL,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding?.artistInfo?.text = spannableString
        binding?.fab?.setOnClickListener {
            val navOptions = AnimationHelper.getNavOptionsWithAnim()
            findNavController().navigate(R.id.action_menu_artist_to_menu_add_task, null, navOptions)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArtistBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainComponent
            .init(App.getApp().appComponent)
            .injectArtistFragment(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
