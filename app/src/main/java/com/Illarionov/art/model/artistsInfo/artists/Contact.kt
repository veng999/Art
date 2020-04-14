package com.Illarionov.art.model.artistsInfo.artists

data class Contact(
    val address: String,
    val position: String,
    val time: String,
    val tz: String,
    val text: String,
    val first: String? = null,
    val last: String? = null,
    val mid: String? = null
)