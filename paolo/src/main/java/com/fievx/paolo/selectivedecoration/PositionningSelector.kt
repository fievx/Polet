package com.fievx.paolo.selectivedecoration

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class PositionningSelector : PositionnningSelectiveDecoration {
    override var includeFirst: Boolean = true
    override var includeLast: Boolean = true
    override var includeFirstInSameTypeGroup: Boolean = true
    override var includeLastInSameTypeGroup: Boolean = true
    override var includeInner: Boolean = true
    override var includeInnerInTypeGroup: Boolean = true

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
        val isLastItem = parent.getChildAdapterPosition(view) == parent.adapter?.itemCount ?: 0 - 1
        val viewType = parent.adapter?.getItemViewType(position)
        val previousViewType = if (position > 0) {
            parent.adapter?.getItemViewType(position - 1)
        } else {
            -1
        }
        val nextViewType = if (position < parent.adapter?.itemCount ?: 0 - 1) {
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

        //check for first item
        if (isFirstItem && includeFirst) {
            return true
        } else if (isFirstItem && !includeFirst) {
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

    fun includeOnlyLastInSameTypeGroup() {
        includeFirst = false
        includeInner = false
        includeInnerInTypeGroup = false
        includeFirstInSameTypeGroup = false
        includeLast = false
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