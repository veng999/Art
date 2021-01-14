package com.Illarionov.art.utils

import com.company.myartist.model.Event
import java.util.*

object DateUtils {
    fun getFormatDate(item: Event?) = item?.getFormatDate(Date().time)
}