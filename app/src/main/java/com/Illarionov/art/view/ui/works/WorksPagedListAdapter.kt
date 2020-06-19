package com.Illarionov.art.view.ui.works

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.Illarionov.art.R
import com.Illarionov.art.holders.WorksHolder
import com.Illarionov.art.model.Work
import com.Illarionov.art.model.WorkAndMedia

class WorksPagedListAdapter(
    diffCallback: DiffUtil.ItemCallback<WorkAndMedia>
): PagedListAdapter<WorkAndMedia, WorksHolder>(diffCallback)  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorksHolder {
        return WorksHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.view_work_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: WorksHolder, position: Int) {
        holder.run { show(getItem(position))  }
    }
}