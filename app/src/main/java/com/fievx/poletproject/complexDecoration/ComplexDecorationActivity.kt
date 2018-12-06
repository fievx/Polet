package com.fievx.poletproject.complexDecoration

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fievx.poletproject.R
import com.fievx.poletproject.adapter.AllTextsAdapter
import com.fievx.polet.Polet
import com.fievx.polet.drawingDecoration.LineDrawingDecoration
import com.fievx.polet.drawingDecoration.PaintingDecoration
import com.fievx.polet.spacingDecoration.SimpleSpacingDecoration

class ComplexDecorationActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        val defaultSpacing = resources.getDimensionPixelSize(R.dimen.spacing_default)
        val black = ContextCompat.getColor(this, R.color.text_default_black)
        val accentColor = ContextCompat.getColor(this, R.color.colorAccent)
        val primaryColor = ContextCompat.getColor(this, R.color.colorPrimary)
        val lineHeight = resources.getDimensionPixelSize(R.dimen.line_height)


        findViewById<RecyclerView>(R.id.rv).apply {
            layoutManager = LinearLayoutManager(
                this@ComplexDecorationActivity,
                RecyclerView.VERTICAL,
                false
            )
            adapter = AllTextsAdapter()


            //Decoration starts here
            addItemDecoration(Polet().apply {
                sizingDecoration = SimpleSpacingDecoration(defaultSpacing)
                drawingDecorations.add(PaintingDecoration(primaryColor))
                drawingDecorations.add(
                    LineDrawingDecoration(accentColor, LineDrawingDecoration.Position.right,defaultSpacing)
                )
                drawingDecorations.add(
                    LineDrawingDecoration(black,LineDrawingDecoration.Position.bottom, lineHeight)
                )
            })
        }
    }
}