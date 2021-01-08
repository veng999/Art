package com.Illarionov.art.view.ui.works

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.Illarionov.art.databinding.ViewWorkItemBinding
import com.Illarionov.art.holders.WorksHolder
import com.company.myartist.model.Work

class WorksPagedListAdapter(
    diffCallback: DiffUtil.ItemCallback<Work>
) : PagedListAdapter<Work, WorksHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        WorksHolder(
             ViewWorkItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: WorksHolder, position: Int) =
        holder.run { show(item = getItem(holder.adapterPosition)) }
}