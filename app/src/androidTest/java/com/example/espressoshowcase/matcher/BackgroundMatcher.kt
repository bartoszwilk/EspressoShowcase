package pl.wp.videostar.util.matcher

import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.view.View
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher


class BackgroundMatcher(@DrawableRes private val resourceId: Int) : TypeSafeMatcher<View>() {

    override fun describeTo(description: Description) {
        description.appendText("with background from resource id: $resourceId")
    }

    override fun matchesSafely(target: View): Boolean {
        if (resourceId < 0) return target.background == null
        val expectedDrawable = ContextCompat.getDrawable(target.context, resourceId)
        return if (expectedDrawable != null && expectedDrawable.constantState != null) {
            expectedDrawable.constantState == target.background.constantState
        } else {
            false
        }
    }
}