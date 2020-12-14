package com.Illarionov.art.view.ui.tasks

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.Illarionov.art.storage.DaoInterface
import com.Illarionov.art.utils.SingleLiveEvent
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.*

private const val DATE_FORMAT = "E dd MMM HH:mm"

class TaskListViewModel(private val dao: DaoInterface) : ViewModel(){

    val cancelTask: MutableLiveData<Int> = SingleLiveEvent()

    fun getTasks() = dao.getTasks()
        .map {
            it.map { entity ->
                with(entity){
                    TaskListItem(
                        id,
                        name,
                        isChecked,
                        dateTime?.let { time -> android.text.format.DateFormat.format(DATE_FORMAT, Date(time))
                        }
                    )
                }
            }
        }.asLiveData(viewModelScope.coroutineContext)

    fun onCheckedTask(id: Long, checked: Boolean){
        if (checked) cancelTask.value = id.toInt()
        viewModelScope.launch {
            dao.setChecked(id, checked)
        }
    }
}