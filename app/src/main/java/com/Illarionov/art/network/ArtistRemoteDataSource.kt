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
import io.reactivex.schedulers.Schedulers


class ArtistRemoteDataSource() : ItemKeyedDataSource<Int, Event>() {

    private val api: ArtistApiService = ArtistApiService.create()
    val initialLoadStateLiveData: MutableLiveData<NetworkState> = MutableLiveData()
    val paginatedStateLiveData : MutableLiveData<NetworkState> = MutableLiveData()
    private val compositeDisposable = CompositeDisposable()
    private var pageNumber = 1
    private val TAG = ArtistRemoteDataSource::class.java.simpleName
    private lateinit var loadParams: LoadParams<Int>
    private lateinit var loadCallback: LoadCallback<Event>


    private fun loadNews (): Observable<EventsResponse> {
        return api.getArtistNews()
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Event>
    ) {
        Log.d(TAG,"Fetching first page: $pageNumber")
        initialLoadStateLiveData.postValue(Loading)
        val showsDisposable = loadNews()
            .subscribeOn(Schedulers.io())
            .doOnError { t: Throwable? -> Log.d(TAG, t!!.message) }
            .subscribe {news -> news.data!!.events?.let {
                onNewsFetched(it, callback) }
            }

        compositeDisposable.add(showsDisposable)
    }

    private fun onNewsFetched (events: List<Event>, callback: LoadInitialCallback<Event>) {
        initialLoadStateLiveData.postValue(Success)
        pageNumber++
        callback.onResult(events)
    }


    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Event>) {
        this.loadParams = params
        this.loadCallback = callback

        Log.d(TAG, "Fetching next page: $pageNumber")
        paginatedStateLiveData.postValue(Loading)
        val showsDisposable = loadNews()
            .subscribeOn(Schedulers.io())
            .doOnError { t: Throwable? -> Log.d(TAG, t!!.message) }
            .subscribe {news -> news.data!!.events?.let {
                onMoreShowsFetched(params, it, callback) }
            }
        compositeDisposable.add(showsDisposable)
    }

    private fun onMoreShowsFetched(params: LoadParams<Int>, events: List<Event>, callback: LoadCallback<Event>) {
        initialLoadStateLiveData.postValue(Success)
        pageNumber++
        callback.onResult(events)
    }


    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback <Event>) {
    }

   override fun getKey(item: Event): Int {
        return pageNumber
    }

    fun clear() {
        compositeDisposable.clear()
        pageNumber = 1
    }

    fun getInitialLoadState() : MutableLiveData<NetworkState> {
        return initialLoadStateLiveData
    }

    fun getPaginatedState() : MutableLiveData<NetworkState> {
        return paginatedStateLiveData
    }

    fun retryPagination() {
        loadAfter(loadParams, loadCallback)
    }
}