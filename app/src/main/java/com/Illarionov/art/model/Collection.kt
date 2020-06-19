package com.company.myartist.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Collection(
    @PrimaryKey
    val set_id: String = "",
    val user_id: String? = null,
    val uri_owner: String? = null,
    val type: String? = null,
    val date: String? = null,
    val name: String? = null,
    val description: String? = null,
    val uri: String? = null,
    val cover_work_id: String? = null,
    @Embedded
    val counters: Counters? = null,
    @Embedded
    val flags: Flags? = null
//    var works: MutableList<Work>? = null
) {

    data class Counters(
        val works: String? = null,
        val likes: String? = null,
        val comments: String? = null
    )

    data class Flags(
        val is_liked: String? = null,
        val is_can_like: String? = null,
        val is_can_comment: String? = null
    )
}