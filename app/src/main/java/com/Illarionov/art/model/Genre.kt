package com.company.myartist.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Genre(
    @PrimaryKey
    val genre_id: String = "",
    val media_id: String? = null,
    val name: String? = null,
    val description: String? = null,
    val uri: String? = null,
    val _extended: String? = null,
    val cnt_works: String? = null
)