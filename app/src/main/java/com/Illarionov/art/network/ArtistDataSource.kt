package com.Illarionov.art.network

import androidx.paging.PageKeyedDataSource
import com.Illarionov.art.rest_api.ArtistApiService

class ArtistDataSource (private val api: ArtistApiService) : PageKeyedDataSource<Int, Event>() {

    val tag = ArtistDataSource::class.java.name
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Event>
    ) {

        api.getArtistNews()
            .subscribeOn()

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Event>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Event>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}