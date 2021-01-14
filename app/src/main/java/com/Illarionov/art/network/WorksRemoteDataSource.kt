package com.Illarionov.art.network

import android.util.Log
import androidx.paging.PositionalDataSource
import com.Illarionov.art.rest_api.ArtistApiService
import com.Illarionov.art.storage.DaoInterface
import com.Illarionov.art.storage.WorkEntity
import com.company.myartist.model.Work
import com.company.myartist.model.response.WorksResponse
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WorksRemoteDataSource(private val dao: DaoInterface) : PositionalDataSource<Work>() {

    private val api: ArtistApiService = ArtistApiService.create()
    private val compositeDisposable = CompositeDisposable()
    private val TAG = WorksRemoteDataSource::class.java.simpleName
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun loadInitial(
        params: LoadInitialParams, callback: LoadInitialCallback<Work>
    ) {
        api.getWorks(offset = 0)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .subscribe(
                {
                    val works = getData(it)
                    scope.launch {
                        dao.saveWorks(WorkEntity(it.data?.works))
                    }
                    callback.onResult(works, 0)
                },
                {
                    Log.e("loadInitial${TAG}", "Fetch events failed: ${it.localizedMessage}")
                }
            ).addTo(compositeDisposable)
    }

    private fun getData(response: WorksResponse?) =
        (response?.data?.getArtWorks() ?: emptyList()).toMutableList()

    private fun getTotalCount(response: WorksResponse?) =
        (response?.data?.count ?: "0").toInt()

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Work>) {
        api.getWorks(offset = params.startPosition)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .subscribe(
                {
                    val data = getData(it)
                    scope.launch(Dispatchers.IO) {
                        dao.saveWorks(WorkEntity(it.data?.works))
                    }
                    callback.onResult(data)
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

