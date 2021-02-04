package com.Illarionov.art.view.ui.tasks

import androidx.lifecycle.*
import com.Illarionov.art.storage.DaoInterface
import com.Illarionov.art.utils.SingleLiveEvent
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

private const val DATE_FORMAT = "E dd MMM HH:mm"

class TaskListViewModel @Inject constructor(private val dao: DaoInterface) : ViewModel(){

    private val _cancelTask: MutableLiveData<Int> = SingleLiveEvent()
    val cancelTask: LiveData<Int> = _cancelTask

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
        if (checked) _cancelTask.value = id.toInt()
        viewModelScope.launch {
            dao.setChecked(id, checked)
        }
    }
}