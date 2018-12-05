package com.fievx.polet.spacingDecoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.fievx.polet.SizingDecoration

class SimpleSpacingDecoration(private val l: Int = 0,
                              private val t: Int = 0,
                              private val r: Int = 0,
                              private val b: Int = 0)
    : SizingDecoration {

    constructor(all: Int = 0): this(all, all, all, all)
    constructor(horizontal: Int = 0, vertical: Int = 0): this(l = horizontal, t = vertical, r = horizontal, b = vertical)

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.apply {
            left = l
            top = t
            right = r
            bottom = b
        }
    }
}