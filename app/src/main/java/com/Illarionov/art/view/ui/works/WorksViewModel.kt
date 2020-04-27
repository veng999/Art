package com.Illarionov.art.view.ui.works

import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.Illarionov.art.network.NewsRemoteDataSource
import com.Illarionov.art.network.WorksRemoteDataSource
import com.company.myartist.model.Work

class WorksViewModel : ViewModel() {

    private val dataSource = NewsRemoteDataSource()
    val worksList = initializedEventsPagedListBuilder().build()

    private fun initializedEventsPagedListBuilder(): LivePagedListBuilder<Long, Work> {

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(10)
            .build()

        val dataSourceFactory = object : DataSource.Factory<Long, Work>() {
            override fun create(): DataSource<Long, Work> {
                return WorksRemoteDataSource() as DataSource<Long, Work>
            }

        }
        return LivePagedListBuilder(dataSourceFactory, config)
    }

    override fun onCleared() {
        super.onCleared()
        dataSource.clear()
    }
}