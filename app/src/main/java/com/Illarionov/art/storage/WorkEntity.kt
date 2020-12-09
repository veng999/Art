package com.Illarionov.art.storage

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.Illarionov.art.converters.WorksConverter
import com.company.myartist.model.Work

@Entity(tableName = "works")
@TypeConverters(WorksConverter::class)
data class WorkEntity(
    @ColumnInfo(name = "works")
    val works: List<Work>?,
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0
)