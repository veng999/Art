package com.company.myartist.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Style(
    @PrimaryKey
    val style_id: String = "",
    val parent_id: String? = null,
    val media_id: String? = null,
    val name: String? = null,
    val description: String? = null,
    val uri: String? = null,
    val _extended: String? = null,
    val cnt_works: String? = null
)