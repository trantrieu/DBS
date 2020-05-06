package com.dbs.uitestsupport

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

object RecyclerViewRobot {

    fun getItemViewAtPosition(recyclerViewId: Int, position: Int, itemViewId: Int = -1): Matcher<View> {
        return object : TypeSafeMatcher<View>() {

            private var recyclerView: RecyclerView? = null
            private var viewItem: ViewGroup? = null

            override fun describeTo(description: Description?) {

            }

            override fun matchesSafely(item: View?): Boolean {
                if (item?.id == recyclerViewId && recyclerView == null) {
                    recyclerView = item as RecyclerView
                }

                if (viewItem == null && recyclerView != null) {
                    val viewHolder = recyclerView!!.findViewHolderForAdapterPosition(position)
                    viewItem = viewHolder?.itemView as ViewGroup?
                }

                if (itemViewId == -1 && item == viewItem && viewItem != null) {
                    return true
                }

                if (itemViewId != -1) {
                    viewItem?.let {
                        val child = it.findViewById<View>(itemViewId)
                        if (child == item && child.id == itemViewId) {
                            return true
                        }
                    }
                }

                return false
            }

        }
    }

}