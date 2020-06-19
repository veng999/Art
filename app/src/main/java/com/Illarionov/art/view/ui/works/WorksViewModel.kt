package com.Illarionov.art.view.ui.works

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.Illarionov.art.repository.MainRepository
import javax.inject.Inject

class WorksViewModel @Inject constructor(workRepository: MainRepository) : ViewModel() {

    private val result = MutableLiveData(workRepository.getWorks())
    val worksList = Transformations.switchMap(result) { it.pagedList }
    val refreshState = Transformations.switchMap(result) { it.refreshState }

    fun refresh() {
        result.value?.refresh?.invoke()
    }
}