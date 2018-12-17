package com.fievx.poletproject.simpleSpacing

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fievx.polet.spacingDecoration.SimpleSpacingDecoration
import com.fievx.polet.Polet
import com.fievx.poletproject.R
import com.fievx.poletproject.adapter.AllTextsAdapter
import com.fievx.polet.decorationSelector.FlexiblePositioningSelector
import com.fievx.polet.decorationSelector.PositioningSelector
import com.fievx.polet.drawingDecoration.PaintingDecoration

class SimpleSpacingDecorationActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        findViewById<RecyclerView>(R.id.rv).apply {
            layoutManager = LinearLayoutManager(this@SimpleSpacingDecorationActivity,
                RecyclerView.VERTICAL,
                false)
            adapter = AllTextsAdapter()

            //Decoration starts here
            val spacing = resources.getDimensionPixelSize(R.dimen.spacing_default)
            addItemDecoration(Polet().apply {
                sizingDecoration = SimpleSpacingDecoration(spacing)
            })

            addItemDecoration(Polet().apply {
                sizingDecoration = SimpleSpacingDecoration(t = spacing * 3)
                selectiveDecoration.add(PositioningSelector().apply {
                    excludeAll()
                    includeFirst = true
                })
            })

            val primary = ContextCompat.getColor(context, R.color.colorPrimary)
            addItemDecoration(Polet().apply {
                drawingDecorations.add(PaintingDecoration(primary))
                selectiveDecoration.add(FlexiblePositioningSelector().apply {
                    skipAll()
                    includeLast = FlexiblePositioningSelector.State.Active
                    includeFirst = FlexiblePositioningSelector.State.Inactive
                })
            })
        }
    }
}