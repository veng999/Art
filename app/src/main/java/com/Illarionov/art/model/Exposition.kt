package com.company.myartist.model

import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset
import org.threeten.bp.format.TextStyle
import java.util.*

data class Exposition(
    val exposition_id: String?,
    val set_id: String?,
    val uri_owner: String?,
    val date_start: String?,
    val date_end: String?,
    val region_id: String?,
    val uri_place: String?,
    val op_times: List<String>?,
    val user_id: String?,
    val f_addr: String?,
    val partner_uris: List<String>?,
    val is_feed: String?,
    val is_confirmed: String?,
    val create_date: String?,
    val is_personal: String?,
    val name: String?,
    val short: String?,
    val address: String?,
    val uri: String?,
    val op_times_str: String?,
    val artist_ids: List<String>?,
    val counters: Counters?,
    val flags: Flags?,
    val ava_id: String?,
    val bg_id: String?,
    val media_ids: List<String>?,
    val coords: Coords?,
    val _extended: String?
) {

    var image: Media? = null

    fun getFormatDate(date: String): String {
        val localDateTime = LocalDateTime.ofEpochSecond(date.toLong(), 0, ZoneOffset.UTC)
        val month = localDateTime.month.getDisplayName(TextStyle.SHORT, Locale("ru"))
        return "${localDateTime.dayOfMonth} ${month}"
    }

    data class Counters(
        val visitors: String?,
        val likes: String?,
        val comments: String?,
        val work: String?,
        val review: String?,
        val discus: String?,
        val photo: String?,
        val video: String?,
        val members: String?
    )

    data class Flags(
        val is_liked: String?,
        val is_can_like: String?,
        val is_can_comment: String?,
        val is_member: String?
    )
}