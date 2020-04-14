package com.company.myartist.model

import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset

data class Event(
    val action: String?,
    val date: String?,
    val ids: List<String>?,
    val is_sended: String?,
    val is_recommendation: String?,
    val user_id: String?,
    val uri_author: String?,
    val style: String?,
    val text: String?,
    val channel_ids: List<String>?,
    val attaches: List<Attach>?,
    val like: Like?,
    val comment: Comment?
) {
    var content: List<Any>? = null

    fun getFormatDate(current: Long): String {
        val currentDateTime = LocalDateTime.ofEpochSecond(current, 0, ZoneOffset.UTC)
        val eventDateTime = LocalDateTime.ofEpochSecond((date ?: "0L").toLong(), 0, ZoneOffset.UTC)

        return when {
            currentDateTime.year - eventDateTime.year > 1 -> "${eventDateTime.year}"
            currentDateTime.year - eventDateTime.year == 1 -> "${eventDateTime.dayOfMonth}.${getFormatMonth(
                eventDateTime
            )}.${eventDateTime.year}"
            currentDateTime.dayOfYear - eventDateTime.dayOfYear == 0 -> "${eventDateTime.hour}:${eventDateTime.minute}"
            else -> "${eventDateTime.dayOfMonth}.${getFormatMonth(eventDateTime)}"
        }
    }

    private fun getFormatMonth(date: LocalDateTime) =
        if (date.monthValue < 10) "0${date.monthValue}" else date.monthValue

    fun getBeforeLastIdEvent() = (ids ?: emptyList()).map { it.toLong() }.min() ?: 0L

    data class Attach(
        val uri: String?,
        val title: String?,
        val media_id: String?
    )

    data class Like(
        val uri: String?,
        val count: String?,
        val can_like: String?,
        val liked: String?
    )

    data class Comment(
        val uri: String?,
        val count: String?,
        val count_new: String?,
        val can_comment: String?
    )
}