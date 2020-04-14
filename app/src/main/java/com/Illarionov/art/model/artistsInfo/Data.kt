package com.Illarionov.art.model.artistsInfo

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data (
    val artistInfo: ArtistInfo
)