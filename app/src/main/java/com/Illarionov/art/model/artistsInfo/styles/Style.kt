package com.Illarionov.art.model.artistsInfo.styles

data class Style(
    val style_id: Int,
    val parent_id: Int,
    val media_id: Int,
    val name: String,
    val description: String,
    val uri: String,
    val _extended: String
)