package com.company.myartist.model.response

import com.company.myartist.model.Collection
import com.company.myartist.model.Media

data class CollectionResponce(
    val data: Data?
) {

    data class Data(
        val count: String?,
        val sets: List<Collection>?,
        val media: List<Media>?
    )
}