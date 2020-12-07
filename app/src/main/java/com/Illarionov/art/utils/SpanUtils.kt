package com.Illarionov.art.utils

import android.text.SpannableString
import android.text.style.StyleSpan

object SpanUtils {
    fun setSpan(text: String, style: StyleSpan, start: Int, end: Int, flags: Int): SpannableString {
        return SpannableString(text).apply {
            setSpan(style, start, end, flags)
        }

    }
}