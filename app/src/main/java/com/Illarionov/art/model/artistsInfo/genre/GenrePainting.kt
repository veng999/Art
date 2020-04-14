package com.Illarionov.art.model.artistsInfo.genre

data class GenrePainting(
    val genre_id: Int,
    val media_id: Int,
    val name: String,
    val description: String,
    val uri: String,
    val _extended: String
)