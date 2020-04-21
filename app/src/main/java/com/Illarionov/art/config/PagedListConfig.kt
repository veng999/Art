package com.Illarionov.art.config

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.Illarionov.art.ArtistDataSourceFactory
import com.company.myartist.model.Event
import java.util.concurrent.Executors

open class PagedListConfig(dataSourceFactory: ArtistDataSourceFactory) {

    companion object {
        const val PAGE_SIZE = 20
        const val INITIAL_LOAD_SIZE_HINT = 5
        const val PREFETCH_DISTANCE = 10
    }

    private val pagedListConfig= PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setInitialLoadSizeHint(INITIAL_LOAD_SIZE_HINT)
        .setPrefetchDistance(PREFETCH_DISTANCE)
        .setPageSize(PAGE_SIZE)
        .build()

    var livePagedListBuilder : LiveData<PagedList<Event>> = LivePagedListBuilder(dataSourceFactory, pagedListConfig)
        .setFetchExecutor(Executors.newSingleThreadExecutor())
        .build()
}
