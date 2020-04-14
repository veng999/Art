package com.Illarionov.art.model.artistsInfo.techniques

data class Technique(
    val technique_id: Int,
    val media_id: Int,
    val name: String,
    val description: String,
    val uri: String,
    val _extended: String
)