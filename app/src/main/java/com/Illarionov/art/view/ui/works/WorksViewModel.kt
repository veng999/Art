package com.Illarionov.art.view.ui.works

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.Illarionov.art.factories.WorksDataSourceFactory
import com.Illarionov.art.rest_api.ArtistApiService
import com.Illarionov.art.storage.DaoInterface
import com.Illarionov.art.utils.SingleLiveEvent
import com.company.myartist.model.Work
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val ENABLE_PLACEHOLDERS = false
private const val PAGE_SIZE = 20

class WorksViewModel @Inject constructor(
    private val dao: DaoInterface,
    private val api: ArtistApiService
) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    private val _error: MutableLiveData<Unit> = SingleLiveEvent()
    private val dataSourceFactory: DataSource.Factory<Long, Work> by lazy { WorksDataSourceFactory(dao, api) }
    val loading: LiveData<Boolean> get() = _loading
    val error: LiveData<Unit> get() = _error
    val worksList: LiveData<PagedList<Work>> by lazy { initializedEventsPagedListBuilder() }

    private fun initializedEventsPagedListBuilder(): LiveData<PagedList<Work>> {

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(ENABLE_PLACEHOLDERS)
            .setPageSize(PAGE_SIZE)
            .build()

        return LivePagedListBuilder(dataSourceFactory, config).build()
    }

    fun onRefresh() {
        _loading.value = false
        _error.value = if (worksList.value.isNullOrEmpty()) Unit else null
        viewModelScope.launch { dao.clearWorks() }
    }
}