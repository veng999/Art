package com.Illarionov.art.model.artistsInfo.artists

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Artist(
    val artist_id: Int,
    val user_id: Int,
    val media_id: Int,
    val bgpic_id: Int,
    val sex: String,
    val owner_type: String,
    val date_add: Long,
    val author_id: Int,
    val dt_birth: String,
    val place_birth_region_id: Int,
    val dt_death: String,
    val place_death_region_id: Int,
    val work_region_id: Long,
    val is_order: String,
    val status: String,
    val is_top: String,
    val is_pro: String,
    val first_name: String,
    val middle_name: String,
    val last_name: String,
    val hello: String,
    val about_app: String,
    val biography: String,
    val uri: String,
    val about_app_html: String,
    val bgpic_default: String,
    val count_works: CountWorks,
    val communities: String? = null,
    val institutions: String? = null,
    val genres: Array<Genre>,
    val school_id: String? = null,
    val types: Array<Type>,
    val styles: Array<Style>,
    val techniques: Array<Technique>,
    val resources: String? = null,
    val counters: Counter,
    val flags: Flag,
    val contact: Contact

)