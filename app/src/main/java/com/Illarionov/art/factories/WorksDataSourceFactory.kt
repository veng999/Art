package com.Illarionov.art.factories

import androidx.paging.DataSource
import com.Illarionov.art.network.WorksRemoteDataSource
import com.Illarionov.art.rest_api.ArtistApiService
import com.Illarionov.art.storage.DaoInterface
import com.company.myartist.model.Work
import javax.inject.Inject

class WorksDataSourceFactory @Inject constructor(
    private val dao: DaoInterface,
    private val api: ArtistApiService
) : DataSource.Factory<Long, Work>() {
    override fun create(): DataSource<Long, Work> =
        WorksRemoteDataSource(dao, api) as DataSource<Long, Work>
}