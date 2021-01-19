package com.Illarionov.art.view.actions

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker
import java.time.Instant
import java.util.*


class SingleDateAndTimePickerAction1 : ViewAction {
    override fun getDescription() = "some description"

    override fun getConstraints() = isAssignableFrom(SingleDateAndTimePicker::class.java)

    override fun perform(uiController: UiController?, view: View?) {
        (view as SingleDateAndTimePicker).apply {
            setDefaultDate(THIS_DATE)
        }
    }

    /*
    * С помощью THIS_DATE устанавливается дата
    * которая выбирается во время теста*/
    companion object {
        private val THIS_DATE = Date.from(Instant.now())
    }
}