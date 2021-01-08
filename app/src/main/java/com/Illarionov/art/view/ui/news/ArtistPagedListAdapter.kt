package com.Illarionov.art.view.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.Illarionov.art.databinding.ViewArtistItemBinding
import com.Illarionov.art.holders.ArtistHolder
import com.company.myartist.model.Event

class ArtistPagedListAdapter(
    diffCallback: DiffUtil.ItemCallback<Event>
    /*val onClickItemListener: OnClickItemListener*/
) : PagedListAdapter<Event, ArtistHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ArtistHolder(
            ViewArtistItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ArtistHolder, position: Int) {
        holder.run { show(item = getItem(position)) }

    }
}



