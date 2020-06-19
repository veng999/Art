package com.Illarionov.art.view.ui.works

import android.util.Log
import androidx.paging.PagedList
import com.Illarionov.art.model.WorkAndMedia
import com.Illarionov.art.rest_api.ArtistApiService
import com.Illarionov.art.utils.PagingRequestHelper
import com.android.example.paging.pagingwithnetwork.reddit.util.createStatusLiveData
import com.company.myartist.model.response.WorksResponse
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.Executors

class WorksBoundaryCallback(
    private val apiService: ArtistApiService,
    private val handleResponse: (response: WorksResponse) -> Completable,
    private val pageSize: Int,
    private var offset: Int
) : PagedList.BoundaryCallback<WorkAndMedia>() {

    private val tag = WorksBoundaryCallback::class.java.name
    private val helper = PagingRequestHelper(Executors.newCachedThreadPool())
    val networkState = helper.createStatusLiveData()

    override fun onZeroItemsLoaded() {
        offset = 0
        loadItems(PagingRequestHelper.RequestType.INITIAL, offset)
    }

    override fun onItemAtEndLoaded(itemAtEnd: WorkAndMedia) {
        super.onItemAtEndLoaded(itemAtEnd)
        loadItems(PagingRequestHelper.RequestType.AFTER, offset)
    }

    private fun loadItems(requestType: PagingRequestHelper.RequestType, offset: Int) {
        helper.runIfNotRunning(requestType) { callback ->
            apiService.getWorks(offset = offset)
                .flatMapCompletable { handleResponse(it) }
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        this.offset += pageSize
                        callback.recordSuccess()
                    },
                    {
                        Log.e(tag, "Fetch works failed: ${it.localizedMessage}")
                        callback.recordFailure(it)
                    }
                )
        }
    }
}