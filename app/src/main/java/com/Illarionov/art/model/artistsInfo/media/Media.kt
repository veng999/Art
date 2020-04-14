package com.Illarionov.art.model.artistsInfo.media

data class Media(
    val media_id: Int,
    val type: String,
    val user_id: Int,
    val caption: String,
    val data: Array<Version>,
    val create_date: Long,
    val use_type: String,
    val uri: String,
    val base_url: String,
    val _extended: String

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Media

        if (media_id != other.media_id) return false
        if (type != other.type) return false
        if (user_id != other.user_id) return false
        if (caption != other.caption) return false
        if (!data.contentEquals(other.data)) return false
        if (create_date != other.create_date) return false
        if (use_type != other.use_type) return false
        if (uri != other.uri) return false
        if (base_url != other.base_url) return false
        if (_extended != other._extended) return false

        return true
    }

    override fun hashCode(): Int {
        var result = media_id
        result = 31 * result + type.hashCode()
        result = 31 * result + user_id
        result = 31 * result + caption.hashCode()
        result = 31 * result + data.contentHashCode()
        result = 31 * result + create_date.hashCode()
        result = 31 * result + use_type.hashCode()
        result = 31 * result + uri.hashCode()
        result = 31 * result + base_url.hashCode()
        result = 31 * result + _extended.hashCode()
        return result
    }
}