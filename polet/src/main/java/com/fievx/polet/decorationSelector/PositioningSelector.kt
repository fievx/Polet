package com.fievx.polet.decorationSelector

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * This DecorationSelector lets you choose which positions should be decorated. For each position, being First, Last,
 *  firstInSameTypeGroup, LastInSameTypeGroup, Inner and InnerInSameTypeGroup ; you can activate or deactivate the decoration
 *
 *  The checks are eager and performed in a fixed order. While this is a performance optimization, it can lead to unsolicited
 *  behaviours when an item satisfies the criterias to multiple positions. In this case, consider using the [FlexiblePositioningSelector]
 *  which has a less eager mechanism.
 */
class PositioningSelector : DecorationSelector {
    var includeFirst: Boolean = true
    var includeLast: Boolean = true
    var includeFirstInSameTypeGroup: Boolean = true
    var includeLastInSameTypeGroup: Boolean = true
    var includeInner: Boolean = true
    var includeInnerInTypeGroup: Boolean = true

    override fun shouldDecorate(view: View, parent: RecyclerView, state: RecyclerView.State): Boolean {
        //if everything is true, we return true immediately for perfs.
        if (includeFirst && includeLast && includeInner && includeFirstInSameTypeGroup &&
            includeLastInSameTypeGroup && includeInnerInTypeGroup
        ) {
            return true
        }

        //gather all necessary info
        val position = parent.getChildAdapterPosition(view)
        val isFirstItem = position == 0
        val isLastItem = parent.getChildAdapterPosition(view) == (parent.adapter?.itemCount ?: 0) - 1
        val viewType = if (position > 0) {
            parent.adapter?.getItemViewType(position)
        } else {
            -1
        }
        val previousViewType = if (position > 0) {
            parent.adapter?.getItemViewType(position - 1)
        } else {
            -1
        }
        val nextViewType = if (position < (parent.adapter?.itemCount ?: 0) - 1) {
            parent.adapter?.getItemViewType(position + 1)
        } else {
            -1
        }

        //!!! order of checks is important. Less likely events should be checked first.

        //check for last item
        if (isLastItem && includeLast) {
            return true
        } else if (isLastItem && !includeLast) {
            return false
        }

        //check for first item
        if (isFirstItem && includeFirst) {
            return true
        } else if (isFirstItem && !includeFirst) {
            return false
        }

        //check for first item in view type group
        val isFirstInSameTypeGroup = (isFirstItem && nextViewType == viewType)
                || (previousViewType != viewType && nextViewType == viewType)
        if (isFirstInSameTypeGroup && includeFirstInSameTypeGroup) {
            return true
        } else if (isFirstInSameTypeGroup && !includeFirstInSameTypeGroup) {
            return false
        }

        //check for last item in view type group
        val isLastItemInSameTypeGroup = (isLastItem && previousViewType == viewType)
                || (previousViewType == viewType && nextViewType != viewType)
        if (isLastItemInSameTypeGroup && includeLastInSameTypeGroup) {
            return true
        } else if (isLastItemInSameTypeGroup && !includeLastInSameTypeGroup) {
            return false
        }



        //check for inner item in view type group
        val isInnerInSameTypeGroup = !isFirstItem && !isLastItem && (previousViewType == viewType)
                && (viewType == nextViewType)
        if (isInnerInSameTypeGroup && includeInnerInTypeGroup) {
            return true
        } else if (isInnerInSameTypeGroup && !includeInnerInTypeGroup) {
            return false
        }

        //check for inner item
        if (!isFirstItem && !isLastItem && includeInner) {
            return true
        }

        return false
    }

    /**
     * Since everything is included by default, you can use this method to exlclude all and re-include
     * selected few places.
     */
    fun excludeAll() {
        includeFirst = false
        includeInner = false
        includeInnerInTypeGroup = false
        includeFirstInSameTypeGroup = false
        includeLast = false
        includeLastInSameTypeGroup = false
    }
}
