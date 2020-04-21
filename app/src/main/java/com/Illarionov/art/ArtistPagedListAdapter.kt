package com.Illarionov.art

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.company.myartist.model.Event

class ArtistPagedListAdapter : PagedListAdapter<Event, ArtistHolder>(ArtistDiffUtilCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistHolder {
        return ArtistHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_artist_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ArtistHolder, position: Int) {
        holder.show(getItem(position))
    }

}