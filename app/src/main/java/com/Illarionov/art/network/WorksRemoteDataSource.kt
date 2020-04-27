package com.Illarionov.art.network

import android.util.Log
import androidx.paging.PositionalDataSource
import com.Illarionov.art.rest_api.ArtistApiService
import com.company.myartist.model.Work
import com.company.myartist.model.response.WorksResponse
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class WorksRemoteDataSource : PositionalDataSource<Work>() {

    private val api: ArtistApiService = ArtistApiService.create()
    private val compositeDisposable = CompositeDisposable()
    private val TAG = WorksRemoteDataSource::class.java.simpleName

    override fun loadInitial(
        params: LoadInitialParams, callback: LoadInitialCallback<Work>
    ) {
        api.getWorks()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .subscribe(
                {
                    val works = getData(it)
                    val position = works.size
                    val totalCount = getTotalCount(it)
                    callback.onResult(works, position, totalCount)
                },
                {
                    Log.e("loadInitial${TAG}", "Fetch events failed: ${it.localizedMessage}")
                }
            ).addTo(compositeDisposable)
    }

    private fun getData(response: WorksResponse?): MutableList<Work> {
        return (response?.data?.getArtWorks() ?: emptyList()).toMutableList()
    }


    private fun getTotalCount(response: WorksResponse?): Int {
        return (response?.data?.count ?: "0").toInt()
    }

    /*override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, Work>) {
        api.getWorks(params.key)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .subscribe(
                {
                    val works = getData(it)
                    callback.onResult(works, params.key + 1)
                },
                {
                    Log.e(TAG, "Fetch events failed: ${it.localizedMessage}")
                }
            ).addTo(compositeDisposable)
    }*/

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Work>) {
        api.getWorks()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .subscribe(
                {
                    val data = getData(it)
                    val startPosition = params.startPosition
                    val loadSize = params.loadSize
                    val subList = data.subList(startPosition, loadSize - 1)
                    callback.onResult(subList)
                },
                {
                    Log.e("loadRange${TAG}", "Fetch events failed: ${it.localizedMessage}")
                }
            ).addTo(compositeDisposable)
    }

    fun clear() {
        compositeDisposable.clear()
    }
}

