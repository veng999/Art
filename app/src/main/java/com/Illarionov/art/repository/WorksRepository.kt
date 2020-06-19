package com.Illarionov.art.repository

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.Illarionov.art.db.ArtistDatabase
import com.Illarionov.art.model.Listing
import com.Illarionov.art.model.NetworkState
import com.Illarionov.art.model.WorkAndMedia
import com.Illarionov.art.rest_api.ArtistApiService
import com.Illarionov.art.view.ui.works.WorksBoundaryCallback
import com.company.myartist.model.response.WorksResponse
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class WorksRepository (
    private val dataBase: ArtistDatabase,
    private val artistApi: ArtistApiService) : MainRepository {
    private val disposable = CompositeDisposable()
    private val tag = WorksRepository::class.java.name
    private val workDao = dataBase.workDao()

    companion object {
        private const val DEFAULT_NETWORK_PAGE_SIZE = 20
    }

    override fun getWorks(): Listing<WorkAndMedia> {
        val refreshTrigger = MutableLiveData<Unit>()
        val refreshState = Transformations.switchMap(refreshTrigger) { refresh() }

        var offset = 0
        workDao.getCountWorks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { offset = it },
                { Log.d(tag, "Error get count works from db: ${it.localizedMessage}") }
            ).addTo(disposable)

        val boundaryCallback = WorksBoundaryCallback(artistApi, this::saveResponse, DEFAULT_NETWORK_PAGE_SIZE, offset)

        val works = MutableLiveData<PagedList<WorkAndMedia>>()
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(DEFAULT_NETWORK_PAGE_SIZE)
            .build()

        RxPagedListBuilder(workDao.findAllWorksWithMedia(), config)
            .setBoundaryCallback(boundaryCallback)
            .buildObservable()
            .subscribe(works::postValue)
            .addTo(disposable)


        return Listing(
            pagedList = works,
            netWorkState = boundaryCallback.networkState,
            refresh = { refreshTrigger.value = null },
            refreshState = refreshState
        )

    }

    @MainThread
    override fun refresh(): LiveData<NetworkState> {
        val networkState = MutableLiveData<NetworkState>()
        networkState.value = NetworkState.LOADING
        clearDataBase()
            .subscribeOn(Schedulers.io())
            .subscribe(
                {
                    networkState.postValue(NetworkState.LOADED)
                },
                {
                    Log.d(tag, "Error refresh works: ${it.localizedMessage}")
                    networkState.postValue(NetworkState.error(it.localizedMessage))
                }
            ).addTo(disposable)
        return networkState
    }

    override fun clearDataBase(): Completable {
        return workDao.deleteAllWorks()
    }

    override fun saveResponse(response: WorksResponse): Completable {
        return dataBase.mediaDao().insertAllMedia(response.data?.media ?: emptyList())
            .andThen(workDao.insertAllWorks(response.data?.works ?: emptyList()))
    }
}