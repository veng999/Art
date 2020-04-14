package com.Illarionov.art.model.artistsInfo.types

data class Type(
    val type_id: Int,
    val media_id: Int,
    val parent_id: Int,
    val name: String,
    val name_object: String,
    val description: String,
    val uri: String,
    val _extended: String
)