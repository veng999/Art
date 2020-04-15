package com.Illarionov.art.network

import android.annotation.SuppressLint
import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.Illarionov.art.rest_api.ArtistApiService
import com.company.myartist.model.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ArtistDataSource (private val api: ArtistApiService) : PageKeyedDataSource<Int, Event>() {

    val tag = ArtistDataSource::class.java.name

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Event>
    ) {

        api.getArtistNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callback.onResult(it.data!!.events!!.toMutableList(), null, null)
            },
                {
                    Log.e(tag, "Fetch data failed: ${it.localizedMessage}")
                })

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Event>) {
        api.getArtistNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                callback.onResult(it.data!!.events!!.toMutableList(), null)
            },
                {
                    Log.e(tag, "Fetch data failed: ${it.localizedMessage}")
                })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Event>) {
    }


}