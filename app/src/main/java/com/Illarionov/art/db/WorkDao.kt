package com.Illarionov.art.db

import androidx.paging.DataSource
import androidx.room.*
import com.Illarionov.art.model.Work
import com.Illarionov.art.model.WorkAndMedia
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface WorkDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllWorks(works: List<Work>): Completable

    @Transaction
    @Query("DELETE FROM work")
    fun deleteAllWorks(): Completable

    @Query("SELECT * FROM work")
    fun findAll(): DataSource.Factory<Int, Work>

    @Query("SELECT COUNT(*) FROM work")
    fun getCountWorks(): Single<Int>

    @Transaction
    @Query("SELECT * FROM work")
    fun findAllWorksWithMedia(): DataSource.Factory<Int, WorkAndMedia>

    /*@Transaction
    @Query("SELECT * FROM work")
    fun findAllWithTechniques(): List<WorkAndTechnique>

    @Transaction
    @Query("SELECT * FROM work")
    fun findAllWithStyles(): List<WorkAndStyle>

    @Transaction
    @Query("SELECT * FROM work")
    fun findAllWithGenres(): List<WorkAndGenre>

    @Transaction
    @Query("SELECT * FROM work")
    fun findAllWithMaterials(): List<WorkAndMaterial>

    @Transaction
    @Query("SELECT * FROM work")
    fun findAllWithCollections(): List<WorkAndCollection>*/
}