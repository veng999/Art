package com.Illarionov.art.view.ui.news

import android.util.Log
import android.view.MenuItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.Illarionov.art.R
import com.Illarionov.art.network.NewsRemoteDataSource
import com.company.myartist.model.Event
import com.google.android.material.bottomnavigation.BottomNavigationView

/*class NewsViewModel(
    *//*var dataSource: ArtistRemoteDataSource*//*
) : BottomNavigationView.OnNavigationItemSelectedListener, ViewModel() {

    val dataSource = NewsRemoteDataSource()
    val TAG = NewsViewModel::class.java.simpleName
    val eventsList = initializedEventsPagedListBuilder().build()

    fun initializedEventsPagedListBuilder(): LivePagedListBuilder<Long, Event> {

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(10)
            .build()

        val dataSourceFactory = object : DataSource.Factory<Long, Event>() {
            override fun create(): DataSource<Long, Event> {
                return NewsRemoteDataSource()
            }

        }
        return LivePagedListBuilder(dataSourceFactory, config)
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_newsFeed -> {
                return true
            }
            else -> false
        }
    }

    fun getNews() : LiveData<PagedList<Event>> {
        return eventsList
    }

    override fun onCleared() {
        super.onCleared()
        dataSource.clear()
        Log.d(TAG, "onCleadred")
    }
}*/



