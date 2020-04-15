package com.Illarionov.art

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.ListAdapter
import com.company.myartist.model.Event

class ArtistAdapter(val onClickItemListener: ArtistRecyclerViewAdapterListener) : ListAdapter<Event, ArtistHolder>(ArtistDiffUtilCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.view_artist_item, parent, false)
        return ArtistHolder(v)
    }


    override fun onBindViewHolder(holder: ArtistHolder, position: Int) {
//        val item = getItem(position)
//        val action = item.action
//        val date = item.date
//        holder.date.text = date
//        holder.action.text = action

        val itemData = getItem(position)
        holder.run {
            show(itemData, object: OnClickItemListener {
                override fun onClickItem(id: String) {
                    onClickItemListener.onClickEvent(id)
                }})
        }


//        holder.show(getItem(position), object: OnClickItemListener {
//            override fun onClickItem(id: String) {
//                onClickItemListener.onClickEvent(id)
//            }
//
//        })
    }


}