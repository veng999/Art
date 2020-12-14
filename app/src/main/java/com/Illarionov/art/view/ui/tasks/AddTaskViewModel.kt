package com.Illarionov.art.view.ui.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.Illarionov.art.storage.DaoInterface
import com.Illarionov.art.storage.TaskEntity
import com.Illarionov.art.utils.SingleLiveEvent
import kotlinx.coroutines.launch
import java.util.*

class AddTaskViewModel(private val dao: DaoInterface) : ViewModel() {

    private var name: String = ""
    private var notifyEnabled = false
    private var notifyTime: Date = Date()
    private val _timeChecked = MutableLiveData<Boolean>()
    private val _result: MutableLiveData<Result> = SingleLiveEvent<Result>()
    private val _saveEnabled = MutableLiveData<Boolean>(false)
    private val _dateTime = MutableLiveData(notifyTime)
    val dateTime: LiveData<Date> = _dateTime
    val timeChecked: LiveData<Boolean> = _timeChecked
    val result: LiveData<Result> = _result
    val saveEnabled: LiveData<Boolean> = _saveEnabled

    fun onNameChanged(text: CharSequence?) {
        name = text?.toString() ?: ""
        _saveEnabled.postValue(!text.isNullOrEmpty())
    }

    fun onTimeCheckedChange(checked: Boolean) {
        _timeChecked.value = checked
        notifyEnabled = checked
    }

    fun onDateChanged(date: Date) {
        notifyTime = date
        _dateTime.value = date
    }

    fun onAddClick() {
        viewModelScope.launch {
            name.let {
                val isNotifyEnabled = if (notifyEnabled) notifyTime.time else null
                val id = dao.saveTasks(TaskEntity(name = name, notifyEnabled = notifyEnabled, dateTime = isNotifyEnabled))
                _result.value = Result.Add(id.toInt(), name, isNotifyEnabled)
            }
        }
    }

    fun onCancelClick() {
        _result.value = Result.Cancel
    }

    sealed class Result {
        object Cancel : Result()
        data class Add(val id: Int, val name: String, val time: Long?) : Result()
    }
}