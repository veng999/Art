package com.Illarionov.art.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Transaction
import com.company.myartist.model.Media
import io.reactivex.Completable

@Dao
interface MediaDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllMedia(medias: List<Media>): Completable
}