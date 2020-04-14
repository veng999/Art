package com.company.myartist.model

data class Work(
    val work_id: String?,
    val user_id: String?,
    val uri_owner: String?,
    val media_id: String?,
    val date_upload: String?,
    val type_id: String?,
    val masterwork: String?,
    val dt_finish: String?,
    val sx: String?,
    val sy: String?,
    val sz: String?,
    val coords: Coords?,
    val is_adult: String?,
    val privacy: String?,
    val name: String?,
    val names: List<String>?,
    val description: String?,
    val artist_hidden_price: String?,
    val uri: String?,
    var tags: List<Tag>?,
    val style_ids: List<String>?,
    val genre_ids: List<String>?,
    val counters: Counters?,
    val collection_id: String?,
    val aset_ids: List<String>?,
    val infos: Infos?,
    val flags: Flags?,
    val colors: Colors?,
    val material_ids: List<String>?,
    val technique_ids: List<String>?,
    val artist_ids: List<String>?,
    val status: Status?,
    val description_html: String?,
    val _extended: String?
) {

    var media: Media? = null
    var techniques: List<Technique>? = null
    var styles: List<Style>? = null
    var materials: List<Material>? = null
    var genres: List<Genre>? = null
    var medias: List<Media>? = null
    var collections: List<Collection>? = null

    fun getFinishedYear(): String {
        return (dt_finish ?: "").split("/").last()
    }

    fun parseAnnotationByParagraphs(title: String): List<Paragraph> {
        val htmlDescription = description_html ?: ""
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
        val middle: String?,
        val pallete: List<String>?
    )

    data class Status(
        val seller: String?,
        val date: String?,
        val region_id: String?,
        val sale_status: String?,
        val old_sale_status: String?,
        val price: String?,
        val currency: String?,
        val is_negotiated: String?,
        val delivery_region: String?,
        val a_date_start: String?,
        val a_count_days: String?,
        val a_date_end: String?,
        val a_price_actual: String?,
        val a_price_min: String?,
        val a_price_buynow: String?,
        val a_antisniper_time: String?,
        val a_auto_restart: String?,
        val auction_id: String?
    ) {
        val saleStatus = sale_status ?: "no_sale"
    }

    data class Counters(
        val selections: String?,
        val comments: String?,
        val likes: String?,
        val audioguides: String?
    )

    data class Flags(
        val is_liked: String?,
        val is_can_like: String?,
        val is_can_comment: String?
    )

    data class Infos(
        val owner_name: String?,
        val collection_name: String?,
        val exposition_id: String?,
        val exposition_name: String?,
        val count_expositions: String?,
        val aset_id: String?,
        val aset_name: String?
    )
}