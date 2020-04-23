package com.Illarionov.art.network

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import com.Illarionov.art.Loading
import com.Illarionov.art.NetworkState
import com.Illarionov.art.Success
import com.Illarionov.art.rest_api.ArtistApiService
import com.company.myartist.model.Event
import com.company.myartist.model.response.EventsResponse
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers


class ArtistRemoteDataSource() : ItemKeyedDataSource<Long, Event>() {

    private val api: ArtistApiService = ArtistApiService.create()
    val initialLoadStateLiveData: MutableLiveData<NetworkState> = MutableLiveData()
    val paginatedStateLiveData : MutableLiveData<NetworkState> = MutableLiveData()
    private val compositeDisposable = CompositeDisposable()
    private val TAG = ArtistRemoteDataSource::class.java.simpleName
    private lateinit var loadParams: LoadParams<Long>
    private lateinit var loadCallback: LoadCallback<Event>


    private fun loadNews (): Observable<EventsResponse> {
        return api.getArtistNews()
    }

    override fun loadInitial(
        params: LoadInitialParams<Long>,
        callback: LoadInitialCallback<Event>
    ) {
        api.getArtistNews()
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .subscribe(
                {
                    val events = getData(it)
                    val position = events.size
                    val totalCount = getTotalCount(it)
                    callback.onResult(events, position, totalCount)
                },
                {
                    Log.e(TAG, "Fetch events failed: ${it.localizedMessage}")
                }
            ).addTo(compositeDisposable)
    }

    private fun getData(response: EventsResponse?): MutableList<Event> {
        return (response?.data?.events ?: emptyList()).toMutableList()
    }

    private fun getTotalCount(response: EventsResponse?): Int {
        return (response?.data?.count ?: "0").toInt()
    }

    private fun onNewsFetched (events: List<Event>, callback: LoadInitialCallback<Event>) {
        initialLoadStateLiveData.postValue(Success)
        callback.onResult(events)
    }


    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<Event>) {
        api.getArtistNews(beforeEventId = params.key)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .subscribe(
                {
                    val events = getData(it)
                    callback.onResult(events)
                },
                {
                    Log.e(TAG, "Fetch events failed: ${it.localizedMessage}")
                }
            ).addTo(compositeDisposable)
    }


    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback <Event>) {
    }

   override fun getKey(item: Event): Long {
        return item.getBeforeLastIdEvent()
    }

    fun clear() {
        compositeDisposable.clear()
    }
}