package com.Illarionov.art

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.Illarionov.art.network.ArtistDataSource
import com.Illarionov.art.rest_api.ArtistApiService
import com.company.myartist.model.Event

class ArtistDataSourceFactory(
    private var service: ArtistApiService
) : DataSource.Factory<Int, Event>() {

    private val mutableDataSource = MutableLiveData<ArtistDataSource>()

    override fun create(): DataSource<Int, Event> {
        val dataSource = ArtistDataSource(service)
        mutableDataSource.postValue(dataSource)
        return dataSource
    }
}