package com.Illarionov.art.view.ui.works

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.Illarionov.art.factories.WorksDataSourceFactory
import com.Illarionov.art.network.NewsRemoteDataSource
import com.Illarionov.art.storage.WorkDao
import com.company.myartist.model.Work

class WorksViewModel(private val dao: WorkDao) : ViewModel() {

    private val dataSource = NewsRemoteDataSource()
    val worksList = initializedEventsPagedListBuilder()

    private fun initializedEventsPagedListBuilder(): LiveData<PagedList<Work>> {

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(10)
            .build()

        val dataSourceFactory = WorksDataSourceFactory(dao)

        return LivePagedListBuilder(dataSourceFactory, config).build()
    }

    override fun onCleared() {
        super.onCleared()
        dataSource.clear()
    }
}