package com.Illarionov.art.stringUtils

import android.text.Html
import android.text.Spanned

class StringUtil {

    fun fromHtml(source: String): Spanned? {
        var source = source
        source = source
            .replace("[\\r\\n]".toRegex(), " ")
            .replace("<\\s*[pP](?:\\s[^>]*)?>\\s*<\\s*/\\s*[pP]\\s*>".toRegex(), " ")
            .replace("\\s\\s+".toRegex(), " ")
            .trim { it <= ' ' }
        return Html.fromHtml(source)
    }
}