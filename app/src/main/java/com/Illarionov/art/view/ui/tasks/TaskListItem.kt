package com.Illarionov.art.view.ui.tasks

data class TaskListItem(
    val id: Long,
    val name: String,
    val isChecked: Boolean,
    val dateTime: CharSequence?
)