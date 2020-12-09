package com.Illarionov.art.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.company.myartist.model.Work
import com.company.myartist.model.response.WorksResponse
import kotlinx.coroutines.flow.Flow

@Dao
interface WorkDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveWorks(response: WorkEntity?): Long

    //@Query("SELECT * FROM works ORDER BY id")
    //suspend fun getWorks(): Flow<List<WorksResponse>>
}
