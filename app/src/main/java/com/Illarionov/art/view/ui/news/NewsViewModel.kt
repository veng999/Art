package com.Illarionov.art.view.ui.news

import android.view.MenuItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.Illarionov.art.ArtistDataSourceFactory
import com.Illarionov.art.R
import com.Illarionov.art.config.PagedListConfig
import com.Illarionov.art.network.ArtistRemoteDataSource
import com.company.myartist.model.Event
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo

class NewsViewModel(
    artistDataSourceFactory: ArtistDataSourceFactory,
    dataSource: ArtistRemoteDataSource,
    backgroundScheduler: Scheduler ) :
    BottomNavigationView.OnNavigationItemSelectedListener, ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val listOfEvents = MutableLiveData<List<Event>>() //todo для работы с постраничным адаптером нужно использовать LivePagedList
    private var listOfPagination : LiveData<PagedList<Event>>

    init{
        listOfPagination = PagedListConfig(artistDataSourceFactory).livePagedListBuilder

        dataSource.loadNews()
            .subscribeOn(backgroundScheduler)
            .subscribe {listOfEvents.postValue(it.data?.events)}
            .addTo(compositeDisposable)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_newsFeed -> {
                return true
            }
            else -> false
        }
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }



}



