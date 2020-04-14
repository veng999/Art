package com.Illarionov.art.model.artistsInfo

import com.Illarionov.art.model.artistsInfo.artists.Artist
import com.Illarionov.art.model.artistsInfo.genre.GenrePainting
import com.Illarionov.art.model.artistsInfo.media.Media
import com.Illarionov.art.model.artistsInfo.techniques.Technique
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ArtistInfo(
    val artists: Array<Artist>,
    val media: Array<Media>,
    val genres: Array<GenrePainting>,
    val types: Array<com.Illarionov.art.model.artistsInfo.types.Type>,
    val styles: Array<com.Illarionov.art.model.artistsInfo.styles.Style>,
    val techniques: Array<Technique>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ArtistInfo

        if (!artists.contentEquals(other.artists)) return false
        if (!media.contentEquals(other.media)) return false
        if (!genres.contentEquals(other.genres)) return false
        if (!types.contentEquals(other.types)) return false
        if (!styles.contentEquals(other.styles)) return false
        if (!techniques.contentEquals(other.techniques)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = artists.contentHashCode()
        result = 31 * result + media.contentHashCode()
        result = 31 * result + genres.contentHashCode()
        result = 31 * result + types.contentHashCode()
        result = 31 * result + styles.contentHashCode()
        result = 31 * result + techniques.contentHashCode()
        return result
    }
}