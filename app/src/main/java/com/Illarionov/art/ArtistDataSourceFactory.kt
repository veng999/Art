package com.Illarionov.art

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.Illarionov.art.network.ArtistDataSource
import com.Illarionov.art.network.ArtistRemoteDataSource
import com.Illarionov.art.rest_api.ArtistApiService
import com.company.myartist.model.Event

class ArtistDataSourceFactory(
    private var service: ArtistApiService
) : DataSource.Factory<Int, Event>() {

    private val mutableDataSource = MutableLiveData<ArtistRemoteDataSource>()

    override fun create(): DataSource<Int, Event> {
        val dataSource = ArtistRemoteDataSource()
        mutableDataSource.postValue(dataSource)
        return dataSource
    }
}