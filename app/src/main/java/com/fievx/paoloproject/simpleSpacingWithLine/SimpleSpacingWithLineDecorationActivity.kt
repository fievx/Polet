package com.fievx.paoloproject.simpleSpacingWithLine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fievx.polet.spacingDecoration.SimpleSpacingDecoration
import com.fievx.polet.Polet
import com.fievx.polet.drawingDecoration.LineDrawingDecoration
import com.fievx.polet.drawingDecoration.PaintingDecoration
import com.fievx.polet.selectiveDecoration.SkippingSelector
import com.fievx.paoloproject.R
import com.fievx.paoloproject.adapter.AllTextsAdapter

class SimpleSpacingWithLineDecorationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        val defaultSpacing = resources.getDimensionPixelSize(R.dimen.spacing_default)
        val black = ContextCompat.getColor(this, R.color.text_default_black)
        val accentColor = ContextCompat.getColor(this, R.color.colorAccent)

        findViewById<RecyclerView>(R.id.rv).apply {
            layoutManager = LinearLayoutManager(
                this@SimpleSpacingWithLineDecorationActivity,
                RecyclerView.VERTICAL,
                false
            )
            adapter = AllTextsAdapter()


            //Decoration starts here
            addItemDecoration(Polet().apply {
                sizingDecoration = SimpleSpacingDecoration(defaultSpacing)
                drawingDecorations.add(
                    LineDrawingDecoration(
                        black,
                        LineDrawingDecoration.Position.bottom,
                        resources.getDimensionPixelSize(R.dimen.line_height)
                    )
                )
                drawingDecorations.add(
                    LineDrawingDecoration(accentColor,
                        LineDrawingDecoration.Position.right,
                        defaultSpacing
                    )
                )
            })

            addItemDecoration(Polet().apply {
                drawingDecorations.add(
                    PaintingDecoration(
                        accentColor
                    )
                )
                selectiveDecoration.add(SkippingSelector(1, 2))
            })
        }
    }
}