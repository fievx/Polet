package com.fievx.paoloproject.simpleSpacingWithLine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fievx.paolo.spacingDecoration.DumbSpacingDecoration
import com.fievx.paolo.Paolo
import com.fievx.paolo.drawingDecoration.LineDrawingDecoration
import com.fievx.paoloproject.R
import com.fievx.paoloproject.adapter.AllTextsAdapter

class SimpleSpacingWithLineDecorationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        findViewById<RecyclerView>(R.id.rv).apply {
            layoutManager = LinearLayoutManager(
                this@SimpleSpacingWithLineDecorationActivity,
                RecyclerView.VERTICAL,
                false
            )
            adapter = AllTextsAdapter()

            //Decoration starts here
            addItemDecoration(Paolo().apply {
                sizingDecoration = DumbSpacingDecoration(resources.getDimensionPixelSize(R.dimen.spacing_default))
                drawingDecorations.add(
                    LineDrawingDecoration(
                        ContextCompat.getColor(
                            this@SimpleSpacingWithLineDecorationActivity,
                            R.color.text_default_black
                        ),
                        LineDrawingDecoration.Position.bottom,
                        resources.getDimensionPixelSize(R.dimen.line_height)
                    )
                )
                drawingDecorations.add(
                    LineDrawingDecoration(
                        ContextCompat.getColor(
                            this@SimpleSpacingWithLineDecorationActivity,
                            R.color.colorAccent
                        ),
                        LineDrawingDecoration.Position.right,
                        resources.getDimensionPixelSize(R.dimen.spacing_default)
                    )
                )
            })
        }
    }
}