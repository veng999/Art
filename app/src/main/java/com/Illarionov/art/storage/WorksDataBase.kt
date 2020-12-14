package com.Illarionov.art.storage

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, exportSchema = false, entities = [WorkEntity::class, TaskEntity::class])
abstract class WorksDataBase : RoomDatabase(){
    abstract fun workDao(): DaoInterface
}