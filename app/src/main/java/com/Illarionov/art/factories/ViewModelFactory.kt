package com.Illarionov.art.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.Illarionov.art.storage.WorksDataBase
import com.Illarionov.art.view.ui.tasks.AddTaskViewModel
import com.Illarionov.art.view.ui.tasks.TaskListViewModel
import com.Illarionov.art.view.ui.works.WorksViewModel

class ViewModelFactory(private val dataBase: WorksDataBase): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
         when(modelClass) {
             AddTaskViewModel::class.java -> AddTaskViewModel(dataBase.workDao())
            WorksViewModel::class.java -> WorksViewModel(dataBase.workDao())
             TaskListViewModel::class.java -> TaskListViewModel(dataBase.workDao())
            else -> throw IllegalAccessException("Can't find $modelClass")
        } as T
}
