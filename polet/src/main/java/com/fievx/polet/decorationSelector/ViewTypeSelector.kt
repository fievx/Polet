package com.fievx.polet.decorationSelector

import android.view.View
import androidx.recyclerview.widget.RecyclerView

open class ViewTypeSelector(vararg viewTypes: Int) : ViewTypeSelectiveDecoration {

    var includedViewTypes = mutableListOf<Int>()
    var excludedViewType = mutableListOf<Int>()

    override fun shouldDecorate(view: View, parent: RecyclerView, state: RecyclerView.State): Boolean {
        val position = parent.getChildAdapterPosition(view)

        if (position == -1) {
            return false
        }

        val viewType: Int
        try {
            viewType = parent.adapter?.getItemViewType(position) ?: -1
        } catch (e: IndexOutOfBoundsException) {
            return false
        }

        if (excludedViewType.contains(viewType)) {
            return false
        }

        return includedViewTypes.contains(viewType) || includedViewTypes.isEmpty()
    }

    override fun exclude(vararg viewTypes: Int) {
        viewTypes.forEach {
            if (!excludedViewType.contains(it)) {
                excludedViewType.add(it)
            }
        }
    }

    override fun include(vararg viewTypes: Int) {
        viewTypes.forEach {
            if (!includedViewTypes.contains(it)) {
                includedViewTypes.add(it)
            }
        }
    }

    init {
        include(*viewTypes)
    }
}