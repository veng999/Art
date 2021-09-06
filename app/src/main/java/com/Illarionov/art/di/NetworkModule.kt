package com.Illarionov.art.di

import com.Illarionov.art.rest_api.ArtistApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideArtistService(): ArtistApiService {
        return ArtistApiService.create()
    }
}