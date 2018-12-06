package com.fievx.polet

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.fievx.polet.decorationSelector.DecorationSelector

/**
 * Entry point to create a decoration.
 */
class Polet: RecyclerView.ItemDecoration(), DecorationSelector {

    var sizingDecoration : SizingDecoration? = null
    val drawingDecorations = mutableListOf<DrawingDecoration>()
    val selectiveDecoration = mutableListOf<DecorationSelector>()

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        for (index in 0 until parent.childCount){
            val view = parent.getChildAt(index)
            if (shouldDecorate(view, parent, state)){
                val rect = Rect()
                getItemOffsets(rect, view, parent, state)
                drawingDecorations.forEach {
                    it.onDraw(rect, view, c, parent, state)
                }
            }
        }
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        if (shouldDecorate(view, parent, state)){
            sizingDecoration?.getItemOffsets(outRect, view, parent, state)
        }
    }

    override fun shouldDecorate(view: View, parent: RecyclerView, state: RecyclerView.State): Boolean {
        selectiveDecoration.forEach {
            @Suppress("SimplifyBooleanWithConstants")
            if (it.shouldDecorate(view, parent, state) == false){
                return false
            }
        }
        return true
    }
}

interface SizingDecoration {
    fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State)
}

interface DrawingDecoration {
    fun onDraw(rect: Rect, view: View, c: Canvas, parent: RecyclerView, state: RecyclerView.State)
}