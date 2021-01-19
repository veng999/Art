package com.Illarionov.art.view


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.Illarionov.art.R
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest23 {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest2() {
        val imageButton = onView(
            allOf(
                withId(R.id.fab), withContentDescription("Add notification"),
                withParent(
                    allOf(
                        withId(R.id.fragment_artist),
                        withParent(withId(R.id.nav_host_fragment))
                    )
                ),
                isDisplayed()
            )
        )
        imageButton.check(matches(isDisplayed()))

        val imageView = onView(
            allOf(
                withContentDescription("Van Gogh image"),
                withParent(
                    allOf(
                        withContentDescription("Van Gogh"),
                        withParent(withId(R.id.app_bar))
                    )
                ),
                isDisplayed()
            )
        )
        imageView.check(matches(isDisplayed()))

        val imageView2 = onView(
            allOf(
                withContentDescription("Van Gogh image"),
                withParent(
                    allOf(
                        withContentDescription("Van Gogh"),
                        withParent(withId(R.id.app_bar))
                    )
                ),
                isDisplayed()
            )
        )
        imageView2.check(matches(isDisplayed()))

        val imageView3 = onView(
            allOf(
                withContentDescription("Van Gogh image"),
                withParent(
                    allOf(
                        withContentDescription("Van Gogh"),
                        withParent(withId(R.id.app_bar))
                    )
                ),
                isDisplayed()
            )
        )
        imageView3.check(matches(isDisplayed()))
    }
}
