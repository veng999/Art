package com.Illarionov.art.utils

import androidx.recyclerview.widget.DiffUtil
import com.company.myartist.model.Event

class NewsDiffUtilItemCallback :  DiffUtil.ItemCallback<Event>() {
    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.getBeforeLastIdEvent() == newItem.getBeforeLastIdEvent()
    }

    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem == newItem
    }
}
