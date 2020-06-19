package com.company.myartist.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tag(
    @PrimaryKey
    val tag_id: String = "",
    val name: String? = null,
    val uri: String? = null,
    val _extended: String? = null,
    val date: String? = null
//    val data: Data? // с сервера приходит или объект Data, или null, или пустой массив
) {

    data class Data(
        val x: String?,
        val y: String?,
        val width: String?,
        val height: String?
    )
}