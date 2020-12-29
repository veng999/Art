package com.Illarionov.art.view.ui.tasks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.Illarionov.art.R
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_task_list.*
import javax.inject.Inject

class TaskListAdapter : RecyclerView.Adapter<TaskListAdapter.ViewHolder>() {
    var items = listOf<TaskListItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_task_list, parent, false)
        )

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bind(item: TaskListItem) {
            with(item) {
                checkbox.isChecked = isChecked
                tvName.text = name
                tvTime.apply {
                    isGone = dateTime.isNullOrEmpty()
                    text = dateTime
                }
            }
        }
    }
}
