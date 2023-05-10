package com.Illarionov.art.extensions

import android.app.Activity
import android.content.Context
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlin.math.roundToInt

inline fun <T> Fragment.observe(data: LiveData<T>, crossinline callback: (T) -> Unit) =
    data.observe(viewLifecycleOwner, Observer { event -> event?.let { callback(it) } })

inline fun <T> AppCompatActivity.observe(data: LiveData<T>, crossinline callback: (T) -> Unit) =
    data.observe(this, Observer { event -> event.let { callback(it) } })

fun Activity.getRootView(): View {
    return findViewById(android.R.id.content)
}

fun Activity.getViewTreeObserver() = getRootView().viewTreeObserver

fun Context.convertDpToPx(dp: Float): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        resources.displayMetrics
    )
}

fun Activity.isKeyboardOpen(): Boolean {
    val visibleBounds = Rect()
    val rootView = getRootView()
    rootView.getWindowVisibleDisplayFrame(visibleBounds)
    val heightDiff = rootView.height - visibleBounds.height()
    val marginOfError = convertDpToPx(50F).roundToInt()
    return heightDiff > marginOfError
}

fun Activity.isKeyboardClosed() = !this.isKeyboardOpen()
