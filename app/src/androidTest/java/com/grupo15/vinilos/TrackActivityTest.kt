package com.grupo15.vinilos


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.`is`
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class TrackActivityTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun trackActivityTest() {
        Thread.sleep(2500)
        val recyclerView = onView(
            allOf(
                withId(R.id.album_recycle),
                childAtPosition(
                    withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                    0
                )
            )
        )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))
        Thread.sleep(2500)
        val materialButton = onView(
            allOf(
                withId(R.id.add_track_button), withText("Agregar track"),
                childAtPosition(
                    childAtPosition(
                        withId(android.R.id.content),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())

        val textView = onView(
            allOf(
                withText("Agregar track"),
                withParent(
                    allOf(
                        withId(androidx.appcompat.R.id.action_bar),
                        withParent(withId(androidx.appcompat.R.id.action_bar_container))
                    )
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Agregar track")))

        val button = onView(
            allOf(
                withId(R.id.add_track_button), withText("AGREGAR TRACK"),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        button.check(matches(isDisplayed()))

        val editText = onView(
            allOf(
                withId(R.id.track_name_label), withText("Nombre"),
                withParent(
                    allOf(
                        withId(R.id.linearLayout),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        editText.check(matches(isDisplayed()))


        val editText2 = onView(
            allOf(
                withId(R.id.track_duration_label), withText("Duración"),
                withParent(
                    allOf(
                        withId(R.id.linearLayout),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        editText2.check(matches(isDisplayed()))

    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
