package com.Illarionov.art.view.ui.news

import android.view.MenuItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.Illarionov.art.ArtistDataSourceFactory
import com.Illarionov.art.NetworkState
import com.Illarionov.art.R
import com.Illarionov.art.config.PagedListConfig
import com.Illarionov.art.network.ArtistRemoteDataSource
import com.company.myartist.model.Event
import com.google.android.material.bottomnavigation.BottomNavigationView

class NewsViewModel(
    var artistDataSourceFactory: ArtistDataSourceFactory,
    var dataSource: ArtistRemoteDataSource
) : BottomNavigationView.OnNavigationItemSelectedListener, ViewModel() {

    var eventsList : LiveData<PagedList<Event>> = PagedListConfig(artistDataSourceFactory).livePagedListBuilder

    init{
        dataSource = artistDataSourceFactory.create() as ArtistRemoteDataSource
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_newsFeed -> {
                return true
            }
            else -> false
        }
    }

    fun initialLoadState(): MutableLiveData<NetworkState> {
        return dataSource.getInitialLoadState()
    }

    fun paginationLoadState() : MutableLiveData<NetworkState> {
        return dataSource.getPaginatedState()
    }

    fun getNews() : LiveData<PagedList<Event>> {
        return eventsList
    }

    override fun onCleared() {
        super.onCleared()
        dataSource.clear()
    }

    fun retry() {
        dataSource.retryPagination()
    }
}



