package com.fievx.polet.decorationSelector

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 *
 */
class DataConditionSelector(val operator: (RecyclerView.Adapter<RecyclerView.ViewHolder>?, position: Int)->(Boolean)): DecorationSelector {
    override fun shouldDecorate(view: View, parent: RecyclerView, state: RecyclerView.State): Boolean {
        val position = parent.getChildAdapterPosition(view)
        return if (position == RecyclerView.NO_POSITION) false else operator.invoke(parent.adapter, position)
    }
}
