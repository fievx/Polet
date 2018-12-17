package com.fievx.polet.decorationSelector

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * This DecorationSelector lets you choose which positions should be decorated. For each position, being First, Last,
 *  firstInSameTypeGroup, LastInSameTypeGroup, Inner and InnerInSameTypeGroup ; you can either make the position Active,
 *  Inactive or Skip.
 *
 *  Active and Inactive are eager, meaning that they will perform similarly as in the [PositioningSelector].
 *  Skip will not draw the decoration, but will not prevent drawing it either.
 *
 *  Example: Say you have a decoration which you don't draw on the first position but only on the last position. A problem
 *  will arise if you only have 1 item in the RV since it will be both first and last. If you were using the [PositioningSelector],
 *  whether the decoration is drawn or not would depend on the order of the checks.
 *  With [FlexiblePositioningSelector] you can mark the first position as Skip. It will not request drawing, but won't prevent
 *  the last position to draw it if needed.
 */
class FlexiblePositioningSelector : DecorationSelector {
    var includeFirst: State = State.Active
    var includeLast: State = State.Active
    var includeFirstInSameTypeGroup: State = State.Active
    var includeLastInSameTypeGroup: State = State.Active
    var includeInner: State = State.Active
    var includeInnerInTypeGroup: State = State.Active

    override fun shouldDecorate(view: View, parent: RecyclerView, state: RecyclerView.State): Boolean {
        //if everything is active, we return true immediately for perfs.
        if (includeFirst == State.Active &&
            includeLast == State.Active &&
            includeInner == State.Active &&
            includeFirstInSameTypeGroup == State.Active &&
            includeLastInSameTypeGroup == State.Active &&
            includeInnerInTypeGroup == State.Active
        ) {
            return true
        }

        //gather all necessary info
        val position = parent.getChildAdapterPosition(view)
        val isFirstItem = position == 0
        val isLastItem = parent.getChildAdapterPosition(view) == (parent.adapter?.itemCount ?: 0) - 1
        val viewType = parent.adapter?.getItemViewType(position)
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

        //check for last item
        if (isLastItem && includeLast == State.Active) {
            return true
        } else if (isLastItem && includeLast == State.Inactive) {
            return false
        }

        //check for first item
        if (isFirstItem && includeFirst == State.Active) {
            return true
        } else if (isFirstItem && includeFirst == State.Inactive) {
            return false
        }

        //check for first item in view type group
        val isFirstInSameTypeGroup = (isFirstItem && nextViewType == viewType)
                || (previousViewType != viewType && nextViewType == viewType)
        if (isFirstInSameTypeGroup && includeFirstInSameTypeGroup == State.Active) {
            return true
        } else if (isFirstInSameTypeGroup && includeFirstInSameTypeGroup == State.Inactive) {
            return false
        }

        //check for last item in view type group
        val isLastItemInSameTypeGroup = (isLastItem && previousViewType == viewType)
                || (previousViewType == viewType && nextViewType != viewType)
        if (isLastItemInSameTypeGroup && includeLastInSameTypeGroup == State.Active) {
            return true
        } else if (isLastItemInSameTypeGroup && includeLastInSameTypeGroup == State.Inactive) {
            return false
        }


        //check for inner item in view type group
        val isInnerInSameTypeGroup = !isFirstItem && !isLastItem && (previousViewType == viewType)
                && (viewType == nextViewType)
        if (isInnerInSameTypeGroup && includeInnerInTypeGroup == State.Active) {
            return true
        } else if (isInnerInSameTypeGroup && includeInnerInTypeGroup == State.Inactive) {
            return false
        }

        //check for inner item
        if (!isFirstItem && !isLastItem && includeInner == State.Active) {
            return true
        }

        return false
    }

    /**
     * Since everything is included by default, you can use this method to exlclude all (make them all Inactive) and re-include
     * selected few places.
     */
    fun excludeAll() {
        includeFirst = State.Inactive
        includeInner = State.Inactive
        includeInnerInTypeGroup = State.Inactive
        includeFirstInSameTypeGroup = State.Inactive
        includeLast = State.Inactive
        includeLastInSameTypeGroup = State.Inactive
    }

    /**
     * Since everything is included by default, you can use this method to skip all and re-include
     * selected few places.
     */
    fun skipAll(){
        includeFirst = State.Skip
        includeInner = State.Skip
        includeInnerInTypeGroup = State.Skip
        includeFirstInSameTypeGroup = State.Skip
        includeLast = State.Skip
        includeLastInSameTypeGroup = State.Skip
    }

    enum class State {
        Active,
        Inactive,
        Skip
    }
}