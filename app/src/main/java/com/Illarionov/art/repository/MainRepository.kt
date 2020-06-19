package com.Illarionov.art.repository

import androidx.lifecycle.LiveData
import com.Illarionov.art.model.Listing
import com.Illarionov.art.model.NetworkState
import com.Illarionov.art.model.WorkAndMedia
import com.company.myartist.model.response.WorksResponse
import io.reactivex.Completable

interface MainRepository {
    fun getWorks(): Listing<WorkAndMedia>
    fun refresh(): LiveData<NetworkState>
    fun clearDataBase(): Completable
    fun saveResponse(response: WorksResponse): Completable
}