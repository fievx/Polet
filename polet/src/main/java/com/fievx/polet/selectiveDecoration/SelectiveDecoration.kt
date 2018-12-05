package com.fievx.polet.selectiveDecoration

import android.view.View
import androidx.recyclerview.widget.RecyclerView

interface SelectiveDecoration {
    fun shouldDecorate(view: View, parent: RecyclerView, state: RecyclerView.State): Boolean
}