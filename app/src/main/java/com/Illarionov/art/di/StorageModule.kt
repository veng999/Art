package com.Illarionov.art.di

import androidx.room.Room
import com.Illarionov.art.App
import com.Illarionov.art.storage.WorksDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

private const val DATABASE_NAME = "database"

@Module
class StorageModule {

    @Provides
    @Singleton
    fun provideWorksDataBase() = Room.databaseBuilder(
        App.getApp().applicationContext,
        WorksDataBase::class.java,
        DATABASE_NAME
    ).build().daoInterface()
}