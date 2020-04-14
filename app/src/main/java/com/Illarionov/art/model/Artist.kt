package com.company.myartist.model

data class Artist(
    val artist_id: String?,
    val user_id: String?,
    val media_id: String?,
    val bgpic_id: String?,
    val sex: String?,
    val owner_type: String?,
    val date_add: String?,
    val author_id: String?,
    val dt_birth: String?,
    val place_birth_region_id: String?,
    val dt_death: String?,
    val place_death_region_id: String?,
    val work_region_id: String?,
    val is_order: String?,
    val status: String?,
    val is_top: String?,
    val is_pro: String?,
    val first_name: String?,
    val middle_name: String?,
    val last_name: String?,
    val biography: String?,
    val hello: String?,
    val about_app: String?,
    val uri: String?,
    val about_app_html: String?,
    val count_works: CountWorks?,
    val communities: String?,
    val institutions: String?,
    var genres: List<Genre>?,
    val school_id: String?,
    val types: List<Type>,
    var styles: List<Style>?,
    var techniques: List<Technique>?,
    val resources: String?,
    val counters: Counters?,
    val flags: Flags?,
    val contact: Contact?
) {
    var avatar: Media? = null

    val fullName: String
        get() = "${first_name ?: ""} ${middle_name ?: ""} ${last_name ?: ""}"

    val countLike: String
        get() = counters?.likes ?: "0"

    val countCollections: String
        get() = counters?.collections ?: "0"

    val countSelections: String
        get() = counters?.selections ?: "0"

    data class Counters(
        val works_like: String?,
        val users_collections: String?,
        val collections: String?,
        val users_selections: String?,
        val selections: String?,
        val artist_sets: String?,
        val histories: String?,
        val real_expositions: String?,
        val likes: String?,
        val comments: String?
    )

    data class Flags(
        val is_liked: String?,
        val is_can_like: String?,
        val is_can_comment: String?,
        val is_can_edit: String?
    )

    data class CountWorks(
        val total: String?,
        val sale: String?,
        val on_map: String?
    )
}