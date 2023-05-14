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
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class CollectorDetailActivityTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun collectorDetailActivityTest() {
        val bottomNavigationItemView = onView(
            allOf(
                withId(R.id.navigation_collector), withContentDescription("Coleccionistas"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_view),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        bottomNavigationItemView.perform(click())

        Thread.sleep(2000)

        val recyclerView = onView(
            allOf(
                withId(R.id.collector_recycle),
                childAtPosition(
                    withClassName(`is`("androidx.constraintlayout.widget.ConstraintLayout")),
                    0
                )
            )
        )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(0, click()))

        Thread.sleep(2000)

        val textView = onView(
            allOf(
                withText("Detalle coleccionista"),
                withParent(
                    allOf(
                        withId(androidx.appcompat.R.id.action_bar),
                        withParent(withId(androidx.appcompat.R.id.action_bar_container))
                    )
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Detalle coleccionista")))

        val textView2 = onView(
            allOf(
                withId(R.id.collector_name_text), withText("Manolo Bellon"),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Manolo Bellon")))

        val textView3 = onView(
            allOf(
                withId(R.id.collector_phone_text), withText("3502457896"),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("3502457896")))

        val textView4 = onView(
            allOf(
                withId(R.id.collector_email_text), withText("manollo@caracol.com.co"),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        textView4.check(matches(withText("manollo@caracol.com.co")))

        val textView5 = onView(
            allOf(
                withId(R.id.collector_favorites_text), withText("Rubén Blades Bellido de Luna"),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        textView5.check(matches(withText("Rubén Blades Bellido de Luna")))

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
