package com.Illarionov.art.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoInterface {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveWorks(response: WorkEntity?): Long

    @Query("DELETE FROM works")
    suspend fun clearWorks()

    @Query("SELECT * FROM works ORDER BY id")
    fun getWorks(): Flow<List<WorkEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTasks(response: TaskEntity?): Long

    @Query("SELECT * FROM tasks ORDER BY id")
    fun getTasks(): Flow<List<TaskEntity>>

    @Query("UPDATE tasks SET is_checked=:checked WHERE id=:id")
    suspend fun setChecked(id: Long, checked: Boolean)

    //@Query("SELECT * FROM works ORDER BY id")
    //suspend fun getWorks(): Flow<List<WorksResponse>>
}
