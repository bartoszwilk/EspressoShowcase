package com.example.espressoshowcase

import android.content.Intent
import android.support.annotation.IdRes
import android.support.test.espresso.Espresso
import android.support.test.espresso.ViewInteraction
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import pl.wp.videostar.util.matcher.BackgroundMatcher
import pl.wp.videostar.util.matcher.DrawableMatcher
import pl.wp.videostar.util.matcher.RecyclerViewMatcher

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Rule
    @JvmField
    var activityTestRule = ActivityTestRule(MainActivity::class.java, false, false)

    @Test
    fun pinnedItemClick_checksCellIfWasUncheckedAndUnchecksIfWasChecked() {
        activityTestRule.launchActivity(Intent())
        checkIfListElementIsSelected(0)
        clickOnListItem(0)
        checkIfListElementIsNotSelected(0)
        clickOnListItem(0)
        checkIfListElementIsSelected(0)
        clickOnListItem(0)
        checkIfListElementIsNotSelected(0)
        clickOnListItem(0)
        checkIfListElementIsSelected(0)
        clickOnListItem(0)
        checkIfListElementIsNotSelected(0)
    }

    fun checkIfListElementIsSelected(position: Int) =
            onRecyclerView(R.id.recyclerView)
                    .apply {
                        atPosition(0).hasBackground(R.drawable.cell_epg_active)
                        onViewAtPosition(R.id.check, 0).hasImageResource(R.drawable.ic_checkbox_checked)
                    }

    fun checkIfListElementIsNotSelected(position: Int) =
            onRecyclerView(R.id.recyclerView)
                    .apply {
                        atPosition(position).hasBackground(R.drawable.cell_epg_normal)
                        onViewAtPosition(R.id.check, position).hasImageResource(R.drawable.ic_checkbox_unchecked)
                    }

    fun clickOnListItem(position: Int) {
        onRecyclerView(R.id.recyclerView)
                .atPosition(0)
                .perform()
                .performClick()
    }

    private fun onRecyclerView(@IdRes recyclerViewId: Int) = RecyclerViewMatcher(recyclerViewId)

    fun RecyclerViewMatcher.atPosition(position: Int) =
            Espresso.onView(this.atPositionOnView(position, -1))

    fun ViewInteraction.hasBackground(@IdRes id: Int) =
            check(ViewAssertions.matches(BackgroundMatcher(id)))

    fun RecyclerViewMatcher.onViewAtPosition(@IdRes id: Int, position: Int) =
            Espresso.onView(this.atPositionOnView(position, id))

    fun ViewInteraction.hasImageResource(@IdRes id: Int) =
            check(ViewAssertions.matches(DrawableMatcher(id)))

    fun ViewInteraction.performClick() = perform(ViewActions.click())
}
