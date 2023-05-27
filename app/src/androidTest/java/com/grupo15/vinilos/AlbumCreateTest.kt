package com.grupo15.vinilos


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class AlbumCreateTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun albumCreateTest() {
        val floatingActionButton = onView(
            allOf(
                withId(R.id.floatingActionButton),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment_activity_main),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        floatingActionButton.perform(click())

        val textView = onView(
            allOf(
                withText("Crear álbum"),
                withParent(
                    allOf(
                        withId(androidx.appcompat.R.id.action_bar),
                        withParent(withId(androidx.appcompat.R.id.action_bar_container))
                    )
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("Crear álbum")))

        val textView2 = onView(
            allOf(
                withId(R.id.album_name_label), withText("Nombre"),
                withParent(
                    allOf(
                        withId(R.id.linearLayout),
                        withParent(withId(R.id.scrollView2))
                    )
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(withText("Nombre")))

        val textView3 = onView(
            allOf(
                withId(R.id.album_cover_label), withText("Portada"),
                withParent(
                    allOf(
                        withId(R.id.linearLayout),
                        withParent(withId(R.id.scrollView2))
                    )
                ),
                isDisplayed()
            )
        )
        textView3.check(matches(withText("Portada")))

        val textView4 = onView(
            allOf(
                withId(R.id.album_release_date_label), withText("Fecha de lanzamiento"),
                withParent(
                    allOf(
                        withId(R.id.linearLayout),
                        withParent(withId(R.id.scrollView2))
                    )
                ),
                isDisplayed()
            )
        )
        textView4.check(matches(withText("Fecha de lanzamiento")))

        val textView5 = onView(
            allOf(
                withId(R.id.album_genre_label), withText("Género"),
                withParent(
                    allOf(
                        withId(R.id.linearLayout),
                        withParent(withId(R.id.scrollView2))
                    )
                ),
                isDisplayed()
            )
        )
        textView5.check(matches(withText("Género")))

        val textView6 = onView(
            allOf(
                withId(R.id.album_record_label), withText("Sello discográfico"),
                withParent(
                    allOf(
                        withId(R.id.linearLayout),
                        withParent(withId(R.id.scrollView2))
                    )
                ),
                isDisplayed()
            )
        )
        textView6.check(matches(withText("Sello discográfico")))

        val textView7 = onView(
            allOf(
                withId(R.id.album_description_label), withText("Descripción"),
                withParent(
                    allOf(
                        withId(R.id.linearLayout),
                        withParent(withId(R.id.scrollView2))
                    )
                ),
                isDisplayed()
            )
        )
        textView7.check(matches(withText("Descripción")))

        val button = onView(
            allOf(
                withId(R.id.add_album_button), withText("CREAR"),
                withParent(withParent(withId(android.R.id.content))),
                isDisplayed()
            )
        )
        button.check(matches(isDisplayed()))

        val imageButton = onView(
            allOf(
                withContentDescription("Navigate up"),
                withParent(
                    allOf(
                        withId(androidx.appcompat.R.id.action_bar),
                        withParent(withId(androidx.appcompat.R.id.action_bar_container))
                    )
                ),
                isDisplayed()
            )
        )
        imageButton.check(matches(isDisplayed()))

        val linearLayout = onView(
            allOf(
                withId(R.id.linearLayout),
                withParent(
                    allOf(
                        withId(R.id.scrollView2),
                        withParent(IsInstanceOf.instanceOf(android.view.ViewGroup::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        linearLayout.check(matches(isDisplayed()))

        val appCompatImageButton = onView(
            allOf(
                withContentDescription("Navigate up"),
                childAtPosition(
                    allOf(
                        withId(androidx.appcompat.R.id.action_bar),
                        childAtPosition(
                            withId(androidx.appcompat.R.id.action_bar_container),
                            0
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        appCompatImageButton.perform(click())

        val imageButton2 = onView(
            allOf(
                withId(R.id.floatingActionButton),
                withParent(withParent(withId(R.id.nav_host_fragment_activity_main))),
                isDisplayed()
            )
        )
        imageButton2.check(matches(isDisplayed()))

        val floatingActionButton2 = onView(
            allOf(
                withId(R.id.floatingActionButton),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment_activity_main),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        floatingActionButton2.perform(click())
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
