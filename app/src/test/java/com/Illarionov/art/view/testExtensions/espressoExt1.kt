package com.Illarionov.art.view.testExtensions

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId

fun onView1(id: Int) = onView(withId(id))

