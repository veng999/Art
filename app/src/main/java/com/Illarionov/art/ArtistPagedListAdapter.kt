package com.Illarionov.art

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.Illarionov.art.R.layout
import com.company.myartist.model.Event

class ArtistPagedListAdapter(
    diffCallback: DiffUtil.ItemCallback<Event>,
    val onClickItemListener: OnClickItemListener
) : PagedListAdapter<Event, ArtistHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistHolder {
        return ArtistHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_artist_item, parent,false))
    }


    override fun onBindViewHolder(holder: ArtistHolder, position: Int) {
        holder.run { show(item = getItem(position)) }

    }
}



