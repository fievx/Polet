package com.fievx.polet.drawingDecoration

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.annotation.ColorInt
import androidx.recyclerview.widget.RecyclerView
import com.fievx.polet.DrawingDecoration

class PaintingDecoration(@ColorInt color: Int): DrawingDecoration {

    private val paint = Paint().apply {
        this.color = color
    }

    override fun onDraw(rect: Rect, view: View, c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        c.drawRect((view.left - rect.left).toFloat(),
            (view.top - rect.top).toFloat(),
            (view.right + rect.right).toFloat(),
            (view.bottom + rect.bottom).toFloat(),
                paint)
    }
}