package com.Illarionov.art.factories

import androidx.paging.DataSource
import com.Illarionov.art.network.WorksRemoteDataSource
import com.Illarionov.art.storage.DaoInterface
import com.company.myartist.model.Work

class WorksDataSourceFactory(private val dao: DaoInterface):  DataSource.Factory<Long, Work>() {
    override fun create(): DataSource<Long, Work> {

        return WorksRemoteDataSource(dao) as DataSource<Long, Work>
    }
}