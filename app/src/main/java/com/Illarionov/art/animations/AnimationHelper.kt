package com.Illarionov.art.animations

import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.RotateAnimation
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import com.Illarionov.art.R

private const val ROTATE_DURATION = 800L
private const val FROM_DEGREES = 0f
private const val TO_DEGREES = 360f
private const val PIVOT_X_VALUE = 0.5f
private const val PIVOT_Y_VALUE = 0.5f

object AnimationHelper {

    fun getNavOptionsWithAnim(): NavOptions {
        return navOptions {
            anim {
                enter = R.anim.slide_in_right
                exit = R.anim.slide_out_left
                popEnter = R.anim.slide_in_left
                popExit = R.anim.slide_out_right
            }
        }
    }

    fun rotateView(view: View) {
        val rotate = RotateAnimation(
            FROM_DEGREES,
            TO_DEGREES,
            Animation.RELATIVE_TO_SELF,
            PIVOT_X_VALUE, Animation.RELATIVE_TO_SELF, PIVOT_Y_VALUE
        )
        rotate.duration = ROTATE_DURATION
        view.startAnimation(rotate)
    }

    fun shakeView(view: View) {
        val animShake = AnimationUtils.loadAnimation(view.context, R.anim.shaking)
        view.startAnimation(animShake)
    }
}