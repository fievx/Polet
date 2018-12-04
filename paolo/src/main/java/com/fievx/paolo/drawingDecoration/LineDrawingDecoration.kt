package com.fievx.paolo.drawingDecoration

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView
import com.fievx.paolo.DrawingDecoration

class LineDrawingDecoration (@ColorInt color: Int, val position: Position, val thickness: Int): DrawingDecoration {
    private val paint = Paint().apply {
        this.color = color
    }

    override fun onDraw(rect: Rect, view: View, c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        when (position){
            Position.top ->
                c.drawRect(view.left - rect.left.toFloat(),
                    view.top - rect.top.toFloat(),
                    view.right + rect.right.toFloat(),
                    view.top - rect.top.toFloat() + thickness.toFloat(),
                    paint)
            Position.bottom ->
                c.drawRect(view.left - rect.left.toFloat(),
                    view.bottom + rect.bottom.toFloat() - thickness.toFloat(),
                    view.right + rect.right.toFloat(),
                    view.bottom + rect.bottom.toFloat(),
                    paint)
            Position.left ->
                c.drawRect(view.left - rect.left.toFloat(),
                    view.top - rect.top.toFloat(),
                    view.left - rect.left.toFloat() + thickness.toFloat(),
                    view.bottom + rect.bottom.toFloat(),
                    paint)
            Position.right ->
                c.drawRect(view.right + rect.right.toFloat() - thickness.toFloat(),
                    view.top - rect.top.toFloat(),
                    view.right + rect.right.toFloat(),
                    view.bottom + rect.bottom.toFloat(),
                    paint)
        }
    }

    enum class Position{
        top,
        bottom,
        left,
        right
    }

}