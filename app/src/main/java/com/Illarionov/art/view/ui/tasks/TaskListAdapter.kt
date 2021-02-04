package com.Illarionov.art.view.ui.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.recyclerview.widget.RecyclerView
import com.Illarionov.art.databinding.ItemTaskListBinding

class TaskListAdapter(val listener: (Long, Boolean) -> Unit) : RecyclerView.Adapter<TaskListAdapter.TaskListViewHolder>() {
    var items = listOf<TaskListItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        TaskListViewHolder(
            ItemTaskListBinding.inflate(
                LayoutInflater.from(parent.context), parent, false), listener)

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: TaskListViewHolder, position: Int) = holder.bind(items[holder.adapterPosition])

    class TaskListViewHolder(
        private val binding: ItemTaskListBinding,
        val listener: (Long, Boolean) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TaskListItem) {
            with(item) {
                with(binding){
                    checkbox.isChecked = isChecked
                    checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
                        listener(id, isChecked)
                    }
                    tvName.text = name
                    tvTime.apply {
                        isGone = dateTime.isNullOrEmpty()
                        text = dateTime
                    }
                }
            }
        }
    }
}
