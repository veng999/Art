package com.company.myartist.model.response

import com.company.myartist.model.Exposition
import com.company.myartist.model.Media

data class ExpositionsResponse(
    val data: Data?
) {
    data class Data(
        val count: String?,
        val expositions: List<Exposition>?,
        val media: List<Media>?
    )
}