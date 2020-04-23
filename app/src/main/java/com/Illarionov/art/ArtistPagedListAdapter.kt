package com.Illarionov.art

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.Illarionov.art.R.layout
import com.Illarionov.art.databinding.LoadingListItemBinding
import com.Illarionov.art.databinding.NetworkFailureListItemBinding
import com.Illarionov.art.view.ui.artist.ArtistViewModel
import com.company.myartist.model.Event

class ArtistPagedListAdapter(
    diffCallback: DiffUtil.ItemCallback<Event>,
    private val callback: Callback
) : PagedListAdapter<Event, ViewHolder>(diffCallback) {

    private lateinit var context: Context
    private var state: NetworkState = Loading
    private val extraRow: Int
        get() = if (hasExtraRow())
            1
        else
            0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val layoutInflater = LayoutInflater.from(context)
        when (viewType) {
            layout.loading_list_item -> {
                val loadingListItemBinding =
                    LoadingListItemBinding.inflate(layoutInflater, parent, false)
                return LoadingHolder(loadingListItemBinding)
            }
            layout.network_failure_list_item -> {
                val networkFailureListItemBinding =
                    NetworkFailureListItemBinding.inflate(layoutInflater, parent, false)
                val networkFailureHolder = NetworkFailureHolder(networkFailureListItemBinding)
                networkFailureHolder.binding.retry.setOnClickListener { callback.onRetryClicked() }
                return networkFailureHolder
            }
        }
        return ArtistHolder (
            LayoutInflater.from(parent.context).inflate(
                R.layout.view_artist_item,
                parent,
                false)
        )
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            layout.view_artist_item -> {
                val news = getItem(position)
                val artistHolder = holder as ArtistHolder
                artistHolder.run {
                    show(news, object : OnClickItemListener {
                        override fun onClickItem(id: String) {
                            /*onClickItemListener.onClickEvent(id)*/

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

    class NetworkFailureHolder(val binding: NetworkFailureListItemBinding) :
        ViewHolder(binding.root)

    class LoadingHolder(val binding: LoadingListItemBinding) : ViewHolder(binding.root)



    interface Callback {
        fun onRetryClicked()
    }
}



