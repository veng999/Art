package com.Illarionov.art.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import javax.inject.Inject

@Database(version = 1, exportSchema = false, entities = [WorkEntity::class, TaskEntity::class])
abstract class WorksDataBase : RoomDatabase(){
    abstract fun daoInterface(): DaoInterface
}