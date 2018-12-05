package com.fievx.polet.selectiveDecoration

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Start decorating at a specified position and then skip a fix number of items between each decoration
 */
class SkippingSelector(private val start: Int = 0, private val skipping: Int = 0) : SelectiveDecoration {
    override fun shouldDecorate(view: View, parent: RecyclerView, state: RecyclerView.State): Boolean {
        val position = parent.getChildAdapterPosition(view)
        return position == start || (position >= start && (position - start) % (skipping + 1) == 0)
    }
}