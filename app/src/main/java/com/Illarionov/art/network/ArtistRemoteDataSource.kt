package com.Illarionov.art.network

import com.Illarionov.art.rest_api.ArtistApiService
import com.company.myartist.model.response.EventsResponse
import io.reactivex.Observable

open class ArtistRemoteDataSource() {

    private val api: ArtistApiService = ArtistApiService.create()

    fun loadNews (): Observable<EventsResponse> {
        return api.getArtistNews()
    }
}