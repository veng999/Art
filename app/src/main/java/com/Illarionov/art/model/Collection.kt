package com.company.myartist.model

data class Collection(
    val set_id: String?,
    val user_id: String?,
    val uri_owner: String?,
    val type: String?,
    val date: String?,
    val name: String?,
    val description: String?,
    val uri: String?,
    val cover_work_id: String?,
    val counters: Counters?,
    val flags: Flags?
) {

    var works: MutableList<Work>? = null

    data class Counters(
        val works: String?,
        val likes: String?,
        val comments: String?
    )

    data class Flags(
        val is_liked: String?,
        val is_can_like: String?,
        val is_can_comment: String?
    )
}