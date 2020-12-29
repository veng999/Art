package com.Illarionov.art.view.ui.works

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.Illarionov.art.R
import com.Illarionov.art.holders.WorksHolder
import com.company.myartist.model.Work
import kotlinx.android.synthetic.main.view_work_item.view.*

class WorksPagedListAdapter(
    diffCallback: DiffUtil.ItemCallback<Work>
) : PagedListAdapter<Work, WorksHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        WorksHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_work_item, parent, false)
        )

    override fun onBindViewHolder(holder: WorksHolder, position: Int) =
        holder.run { show(item = getItem(position)) }
}