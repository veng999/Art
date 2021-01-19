package com.Illarionov.art.view

import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.Illarionov.art.R
import com.Illarionov.art.utils.AnimationHelper
import com.Illarionov.art.view.actions.SingleDateAndTimePickerAction1
import com.google.common.truth.Truth.assertThat
import org.hamcrest.core.AllOf.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

private const val DEFAULT_SLEEP = 1000L
private const val TEST_STRING = "The best exhibition"

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class ArtNavigationTest1 {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    //testNavHostController.navigate() doesn't trigger the underlying navigate() behavior
    private val testNavHostController by lazy {
        TestNavHostController(
            ApplicationProvider.getApplicationContext()
        ).apply { setGraph(R.navigation.mobile_navigation) }
    }

    @Test
    fun testAddTask() {
        val navOptions = AnimationHelper.getNavOptionsWithAnim()
        //Check biography textView's text
        //onView1(R.id.artist_info).check(matches(withText(R.string.biography)))

        //Check that fab is displayed and clickable and then click
        //onView1(R.id.fab).check(matches(allOf(isDisplayed(), isClickable())))
            //.perform(click())

        //Check that our navigation's destination id is equals R.id.menu_add_task
        assertThat(testNavHostController.currentDestination?.id).isEqualTo(R.id.menu_artist)

        //Navigate to AddTaskFragment
        testNavHostController.navigate(R.id.menu_add_task, null, navOptions)
        assertThat(testNavHostController.currentDestination?.id).isEqualTo(R.id.menu_add_task)
        sleep(DEFAULT_SLEEP)

        //Check that TextInputEditText displayed and focusable + include resource string
        //onView1(R.id.etName)
            /*.check(matches(allOf(isDisplayed(), isFocusable())))
            .perform(typeText(TEST_STRING))*/

        sleep(DEFAULT_SLEEP)

        /*onView1(R.id.switchTime)
            .check(matches(allOf(isDisplayed(), isFocusable(), isClickable())))
            .perform(closeSoftKeyboard())
            .perform(click())*/

        sleep(DEFAULT_SLEEP)

        /*onView1(R.id.datePicker)
            .check(matches(isDisplayed()))
            .perform(SingleDateAndTimePickerAction1())

        sleep(DEFAULT_SLEEP)

        onView1(R.id.addButton)
            .check(matches(allOf(isDisplayed(), isClickable())))
            .perform(click())*/

        sleep(DEFAULT_SLEEP)

        testNavHostController.navigate(R.id.menu_tasks_list, null, navOptions)
        assertThat(testNavHostController.currentDestination?.id).isEqualTo(R.id.menu_tasks_list)

        sleep(DEFAULT_SLEEP)

    }

    private fun sleep(time: Long) = Thread.sleep(time)

}