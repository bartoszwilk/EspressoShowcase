package pl.wp.videostar.util.matcher

import android.content.res.Resources
import android.support.v7.widget.RecyclerView
import android.view.View
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

class RecyclerViewMatcher(val recyclerViewId: Int) {

    fun atPositionOnView(position: Int, targetViewId: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {

            private var resources: Resources? = null

            override fun describeTo(description: Description) {
                var idDescription = Integer.toString(recyclerViewId)
                if (this.resources != null) {
                    idDescription = try {
                        this.resources!!.getResourceName(recyclerViewId)
                    } catch (var4: Resources.NotFoundException) {
                        String.format("%s (resource name not found)", recyclerViewId)
                    }

                }
                description.appendText("with id: " + idDescription)
                description.appendText("with position: " + position)
            }

            public override fun matchesSafely(view: View): Boolean {
                this.resources = view.resources
                val recyclerView = view.rootView.findViewById(recyclerViewId) as RecyclerView
                return view === recyclerView
                        .findViewHolderForAdapterPosition(position)
                        ?.itemView
                        ?.let { if (targetViewId > -1) it.findViewById(targetViewId) else it }
                        ?: false
            }
        }
    }
}