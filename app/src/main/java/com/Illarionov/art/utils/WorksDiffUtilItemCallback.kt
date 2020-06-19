package com.Illarionov.art.utils

import androidx.recyclerview.widget.DiffUtil
import com.Illarionov.art.model.Work

class WorksDiffUtilItemCallback : DiffUtil.ItemCallback<Work>() {
    override fun areItemsTheSame(oldItem: Work, newItem: Work): Boolean {
        return oldItem.work_id == newItem.work_id
    }

    override fun areContentsTheSame(oldItem: Work, newItem: Work): Boolean {
        return newItem == oldItem
    }

}