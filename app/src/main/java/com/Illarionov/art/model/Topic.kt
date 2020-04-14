package com.company.myartist.model

data class Topic(
    val topic_id: String?,
    val uri_owner: String?,
    val uri_author: String?,
    val repost_topic_id: String?,
    val user_id: String?,
    val type: String?,
    val status: String?,
    val date: String?,
    val uris: List<String>?,
    val title: String?,
    val small: String?,
    val full: String?,
    val uri: String?,
    val video_links: List<String>?,
    val rating: Rating?,
    val counters: Counters?,
    val flags: Flags?,
    val first_media_id: String?,
    val _extended: String?
) {
    var media: List<Media>? = null

    data class Rating(
        val type: String?,
        val rating: String?,
        val is_voted: String?,
        val count_votes: String?
    )

    data class Counters(
        val rating_sys: String?,
        val visitors: String?,
        val comments: String?,
        val likes: String?
    )

    data class Flags(
        val is_liked: String?,
        val is_can_like: String?,
        val is_can_comment: String?,
        val is_reposted: String?
    )
}