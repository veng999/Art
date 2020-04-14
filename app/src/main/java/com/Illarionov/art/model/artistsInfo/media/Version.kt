package com.Illarionov.art.model.artistsInfo.media

data class Version(
    val version: String,
    val version_big: String,
    val version_orig: String,
    val sizes: Orig,
    val x: Int,
    val y: Int,
    val ratio: Double,
    val ext: String,
    val is_animated: Boolean
)