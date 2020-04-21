package com.Illarionov.art

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.Illarionov.art.rest_api.ArtistApiService
import com.company.myartist.model.Event

class ArtistDataSource(val context: Context) : PageKeyedDataSource<Int, Event>() {

    val TAG = ArtistDataSource::class.java.simpleName
    private val restApi = ArtistApiService.create()


    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Event>
    ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Event>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Event>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}