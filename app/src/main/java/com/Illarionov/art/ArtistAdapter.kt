package com.Illarionov.art


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.company.myartist.model.Event

class ArtistAdapter(val onClickItemListener: ArtistRecyclerViewAdapterListener) : ListAdapter<Event, ArtistHolder>(ArtistDiffUtilCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.view_artist_item, parent, false)
        return ArtistHolder(v)
    }


    override fun onBindViewHolder(holder: ArtistHolder, position: Int) {

        val itemData = getItem(position)
        holder.run {
            show(itemData, object: OnClickItemListener {
                override fun onClickItem(id: String) {
                    onClickItemListener.onClickEvent(id)
                }})
        }
    }
}