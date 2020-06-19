package com.company.myartist.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Material(
    @PrimaryKey
    val material_id: String = "",
    val media_id: String? = null,
    val name: String? = null,
    val uri: String? = null,
    val _extended: String? = null
)