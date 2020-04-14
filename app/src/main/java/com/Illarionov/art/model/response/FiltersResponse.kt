package com.company.myartist.model.response

import com.company.myartist.model.Filter
import com.company.myartist.model.Work

data class FiltersResponse(
    val data: Data?
) {

    data class Data(
        val count: String?,
        val works: List<Work>?,
        val filters: List<Filter>?
    ) {
        fun getFiltersForWorks(): List<Filter> {
            return filters ?: emptyList()
        }
    }
}