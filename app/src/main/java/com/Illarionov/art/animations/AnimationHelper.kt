package com.Illarionov.art.animations

import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.view.animation.ScaleAnimation
import androidx.navigation.NavOptions
import androidx.navigation.navOptions
import com.Illarionov.art.R

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
            0f,
            360f,
            Animation.RELATIVE_TO_SELF,
            1f, Animation.RELATIVE_TO_SELF, 1f
        )
        rotate.duration = 1500
        view.startAnimation(rotate)
    }

    fun changeScaleOfView(view: View) {
        val scaleAnimation = ScaleAnimation(
            1f,
            4.0f,
            1f,
            4.0f,
            Animation.RELATIVE_TO_SELF, 2f, // Pivot point of X scaling
            Animation.RELATIVE_TO_SELF, 1f
        )
        scaleAnimation.duration = 2000
        view.startAnimation(scaleAnimation)
    }
}