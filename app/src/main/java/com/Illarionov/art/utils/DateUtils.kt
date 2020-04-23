package com.Illarionov.art.utils

import com.company.myartist.model.Event
import java.util.*

object DateUtils {
    fun getFormatDate(item: Event?): String? {
        return item?.getFormatDate(Date().time)
    }
}