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
) : PagedListAdapter<Event, ViewHolder>(diffCallback) {

    private lateinit var artistHolder: ArtistHolder
    private var state: NetworkState = Loading
    private val extraRow: Int
        get() = if (hasExtraRow())
            1
        else
            0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        /*when (viewType) {
            layout.loading_list_item -> {
                 artistHolder = ArtistHolder(
                    LayoutInflater.from(context).inflate(
                        R.layout.view_artist_item,
                        parent,
                        false
                    )
                )
            }
        }*/
        return ArtistHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_news_feed, parent,false))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            layout.view_artist_item -> {
                val news = getItem(position)
                val artistHolder = holder as ArtistHolder
                artistHolder.run {
                    show(news, object : OnClickItemListener {
                        override fun onClickItem(id: String) {
                            onClickItemListener.onClickItem(id)
                        }
                    })
                }
            }
        }
    }

    fun setNetworkState(networkState: NetworkState) {
        val previousState = state
        val hadExtraRow = hasExtraRow()
        state = networkState
        val hasExtraRow = hasExtraRow()
        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(super.getItemCount())
            } else {
                notifyItemInserted(super.getItemCount())
            }
        } else if (hasExtraRow && previousState != state) {
            notifyItemChanged(itemCount - 1)
        }
    }

    override fun getItemViewType(position: Int): Int {
        // Reached at the end
        return if (hasExtraRow() && position == itemCount - 1) {
            if (state === Loading) {
                layout.loading_list_item
            } else {
                layout.network_failure_list_item
            }
        } else {
            layout.fragment_news_feed
        }
    }


    override fun getItemCount(): Int {
        return super.getItemCount() + extraRow
    }

    private fun hasExtraRow(): Boolean {
        return state == Success
    }




    interface Callback {
        fun onRetryClicked()
    }
}



