package com.fievx.polet.decorationSelector

import android.view.View
import androidx.recyclerview.widget.RecyclerView

interface DecorationSelector {
    fun shouldDecorate(view: View, parent: RecyclerView, state: RecyclerView.State): Boolean
}