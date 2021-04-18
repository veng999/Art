package com.Illarionov.art.listeners

import android.view.ViewTreeObserver
import androidx.annotation.CallSuper
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.Illarionov.art.extensions.getViewTreeObserver
import com.Illarionov.art.extensions.isKeyboardOpen

class ArtKeyEventListener(
    private val activity: FragmentActivity,
    private val callback: (isOpen: Boolean) -> Unit
) : LifecycleObserver {

    private val listener = object : ViewTreeObserver.OnGlobalLayoutListener {
        private var lastState: Boolean = activity.isKeyboardOpen()

        override fun onGlobalLayout() {
            val isOpen = activity.isKeyboardOpen()
            if (isOpen == lastState) {
                return
            } else {
                dispatchKeyboardEvent(isOpen)
                lastState = isOpen
            }
        }
    }

    init {
        // Dispatch the current state of the keyboard
        dispatchKeyboardEvent(activity.isKeyboardOpen())
        // Make the component lifecycle aware
        activity.lifecycle.addObserver(this)
        registerKeyboardListener()
    }

    private fun dispatchKeyboardEvent(isOpen: Boolean) = callback(isOpen)

    @OnLifecycleEvent(value = Lifecycle.Event.ON_PAUSE)
    @CallSuper
    fun onLifecyclePause()  = unregisterKeyboardListener()

    private fun unregisterKeyboardListener() {
        activity.getViewTreeObserver().removeOnGlobalLayoutListener(listener)
    }

    private fun registerKeyboardListener() {
        activity.getViewTreeObserver().addOnGlobalLayoutListener(listener)
    }
}