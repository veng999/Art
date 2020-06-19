package com.Illarionov.art.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import com.company.myartist.model.Media

@Entity
data class WorkAndMedia(
    @Embedded
    val work: Work,
    @Relation(
        parentColumn = "media_id",
        entityColumn = "media_id"
    )
    val media: Media? = null
)