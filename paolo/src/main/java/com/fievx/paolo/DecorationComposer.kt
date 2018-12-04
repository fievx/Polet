package com.fievx.paolo

import android.graphics.Canvas
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.fievx.paolo.selectivedecoration.SelectiveDecoration

class DecorationComposer: RecyclerView.ItemDecoration(), SelectiveDecoration {

    var sizingDecoration : SizingDecoration? = null
    val drawingDecorations = mutableListOf<DrawingDecoration>()
    val selectors = mutableListOf<SelectiveDecoration>()

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
        selectors.forEach {
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