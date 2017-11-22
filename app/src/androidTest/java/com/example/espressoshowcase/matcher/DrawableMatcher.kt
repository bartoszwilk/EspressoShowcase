package pl.wp.videostar.util.matcher

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.support.v4.graphics.drawable.DrawableCompat
import android.view.View
import android.widget.ImageView
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher


class DrawableMatcher(@DrawableRes private val resourceId: Int) : TypeSafeMatcher<View>() {

    override fun describeTo(description: Description) {
        description.appendText("with drawable from resource id: $resourceId")
    }

    override fun matchesSafely(target: View): Boolean {
        if (target !is ImageView) {
            return false
        }
        if (resourceId < 0) {
            return target.drawable == null
        }
        var expectedDrawable = ContextCompat.getDrawable(target.context, resourceId)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) expectedDrawable = DrawableCompat.wrap(expectedDrawable).mutate()
        if (expectedDrawable == null) return false

        val bitmap = getBitmap(target.drawable)
        val otherBitmap = getBitmap(expectedDrawable)
        return bitmap.sameAs(otherBitmap)
    }

    private fun getBitmap(drawable: Drawable) =
            Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888).apply {
                with(Canvas(this)) {
                    drawable.setBounds(0, 0, width, height)
                    drawable.draw(this)
                }
            }
}