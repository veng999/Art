package com.Illarionov.art.view.ui.works

import android.util.Log
import android.view.MenuItem
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.Illarionov.art.R
import com.Illarionov.art.network.NewsRemoteDataSource
import com.Illarionov.art.network.WorksRemoteDataSource
import com.company.myartist.model.Work
import com.google.android.material.bottomnavigation.BottomNavigationView

class WorksViewModel : ViewModel(), BottomNavigationView.OnNavigationItemSelectedListener {

    val dataSource = NewsRemoteDataSource()
    val TAG = WorksViewModel::class.java.simpleName
    val worksList = initializedEventsPagedListBuilder().build()

    fun initializedEventsPagedListBuilder(): LivePagedListBuilder<Long, Work> {

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(20)
            .build()

        val dataSourceFactory = object : DataSource.Factory<Long, Work>() {
            override fun create(): DataSource<Long, Work> {
                return WorksRemoteDataSource()
            }

        }
        return LivePagedListBuilder(dataSourceFactory, config)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_works -> {
                return true
            }
            else -> false
        }
    }


    override fun onCleared() {
        super.onCleared()
        dataSource.clear()
        Log.d(TAG, "onCleadred")
    }
}