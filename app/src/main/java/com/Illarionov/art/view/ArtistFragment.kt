package com.Illarionov.art.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.Illarionov.art.R
import com.Illarionov.art.viewModel.ArtistViewModel

class ArtistFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_news_feed, container, false)
        val cardView = rootView.findViewById<CardView>(R.id.card_view)
        return rootView
    }
}