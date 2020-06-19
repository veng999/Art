package com.Illarionov.art.di

import com.Illarionov.art.db.ArtistDatabase
import com.Illarionov.art.repository.WorksRepository
import com.Illarionov.art.rest_api.ArtistApiService
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun workRepository(
        apiService: ArtistApiService,
        db: ArtistDatabase
    ) : WorksRepository{
        return WorksRepository(db, apiService)
    }
}