package com.Illarionov.art.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.company.myartist.model.Coords
import com.company.myartist.model.Paragraph
import com.company.myartist.model.Tag
import com.google.gson.annotations.SerializedName

@Entity
data class Work(
    @PrimaryKey
    var work_id: String = "",
    var user_id: String? = null,
    var uri_owner: String? = null,
    var media_id: String? = null,
    var date_upload: String? = null,
    var type_id: String? = null,
    var masterwork: String? = null,
    var dt_finish: String? = null,
    var sx: String? = null,
    var sy: String? = null,
    var sz: String? = null,
    @SerializedName("is_adult")
    var isadult: String? = null,
    @Embedded
    var coords: Coords? = null,
    var privacy: String? = null,
    var name: String? = null,
    var names: List<String>? = null,
    var description: String? = null,
    var artist_hidden_price: String? = null,
    var uri: String? = null,
    @Ignore
    var tags: List<Tag>? = null,
    var style_ids: List<String>? = null,
    var genre_ids: List<String>? = null,
    @Embedded
    var counters: Counters? = null,
    var collection_id: String? = null,
    var aset_ids: List<String>? = null,
    @Embedded
    var infos: Infos? = null,
    @Embedded
    var flags: Flags? = null,
    @Embedded
    var colors: Colors? = null,
    var material_ids: List<String>? = null,
    var technique_ids: List<String>? = null,
    var artist_ids: List<String>? = null,
    @Embedded
    var status: Status? = null,
    var description_html: String? = null,
    var _extended: String? = null
/*    var media: Media? = null*/
//    var techniques: List<Technique>? = null,
//    var styles: List<Style>? = null,
//    var materials: List<Material>? = null,
//    var genres: List<Genre>? = null,
//    var medias: List<Media>? = null,
//    var collections: List<Collection>? = null
) {

    fun getFinishedYear(): String {
        return (dt_finish ?: "").split("/").last()
    }

    fun parseAnnotationByParagraphs(title: String): List<Paragraph> {
        var htmlDescription = description_html ?: ""
        return htmlDescription.split("<h2>")
            .map { it.split("</h2>") }
            .map {
                if (it.size == 0) {
                    Paragraph(title, it[0])
                } else {
                    Paragraph(it[0], it[1])
                }
            }
            .toList()
    }

    data class Colors(
        var middle: String?,
        var pallete: List<String>?
    )

    data class Status(
        var seller: String?,
        var date: String?,
        var region_id: String?,
        var sale_status: String?,
        var old_sale_status: String?,
        var price: String?,
        var currency: String?,
        var is_negotiated: String?,
        var delivery_region: String?,
        var a_date_start: String?,
        var a_count_days: String?,
        var a_date_end: String?,
        var a_price_actual: String?,
        var a_price_min: String?,
        var a_price_buynow: String?,
        var a_antisniper_time: String?,
        var a_auto_restart: String?,
        var auction_id: String?
    ) {
        @Ignore
        var saleStatus = sale_status ?: "no_sale"
    }

    data class Counters(
        var selections: String?,
        var comments: String?,
        var likes: String?,
        var audioguides: String?
    )

    data class Flags(
        var is_liked: String?,
        var is_can_like: String?,
        var is_can_comment: String?
    )

    data class Infos(
        var owner_name: String?,
        var collection_name: String?,
        var exposition_id: String?,
        var exposition_name: String?,
        var count_expositions: String?,
        var aset_id: String?,
        var aset_name: String?
    )
}