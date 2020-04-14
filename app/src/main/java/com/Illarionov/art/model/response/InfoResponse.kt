package com.company.myartist.model.response

data class InfoResponse(
    val data: Data?
) {

    data class Data(
        val text: String?
    )
}