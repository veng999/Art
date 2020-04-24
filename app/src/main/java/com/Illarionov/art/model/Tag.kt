package com.company.myartist.model

data class Tag(
    val tag_id: String?,
    val name: String?,
    val uri: String?,
    val _extended: String?,
    val date: String?
//    val data: Data? // с сервера приходит или объект Data, или null, или пустой массив
) {

    data class Data(
        val x: String?,
        val y: String?,
        val width: String?,
        val height: String?
    )
}