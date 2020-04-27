package com.Illarionov.art.network

import android.util.Log
import androidx.paging.ItemKeyedDataSource
import androidx.paging.PageKeyedDataSource
import com.Illarionov.art.rest_api.ArtistApiService
import com.company.myartist.model.Work
import com.company.myartist.model.response.WorksResponse
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class WorksRemoteDataSource : PageKeyedDataSource<Long, Work>() {

    private val api: ArtistApiService = ArtistApiService.create()
    private val compositeDisposable = CompositeDisposable()
    private val TAG = WorksRemoteDataSource::class.java.simpleName

    //todo список не подгружается по странично проверь запросы в логе и тот ли ты DataSource выбрал?
    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Long, Work>
    ) {
        api.getWorks()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .subscribe(
            {
                val works = getData(it)
                val position = works.size
                val totalCount = getTotalCount(it)
                callback.onResult(works, position.toLong(), totalCount.toLong())
            },
            {
                Log.e(TAG, "Fetch events failed: ${it.localizedMessage}")
            }
        ).addTo(compositeDisposable)
    }

    private fun getData(response: WorksResponse?): MutableList<Work> {
        return (response?.data?.getArtWorks() ?: emptyList()).toMutableList()
    }

    private fun getTotalCount(response: WorksResponse?): Int {
        return (response?.data?.count ?: "0").toInt()
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Long, Work>) {
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
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<Long, Work>) {
    }

    fun clear() {
        compositeDisposable.clear()
    }
}