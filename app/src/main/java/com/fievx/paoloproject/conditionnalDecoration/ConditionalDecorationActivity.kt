package com.fievx.paoloproject.conditionnalDecoration

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fievx.paoloproject.R
import com.fievx.paoloproject.adapter.RandomNumberAdapter
import com.fievx.polet.Polet
import com.fievx.polet.drawingDecoration.PaintingDecoration
import com.fievx.polet.decorationSelector.DataConditionSelector
import com.fievx.polet.spacingDecoration.SimpleSpacingDecoration

class ConditionalDecorationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        val defaultSpacing = resources.getDimensionPixelSize(R.dimen.spacing_default)
        val primaryColor = ContextCompat.getColor(this, R.color.colorPrimary)
        val accentColor = ContextCompat.getColor(this, R.color.colorAccent)

        findViewById<RecyclerView>(R.id.rv).apply {
            layoutManager = LinearLayoutManager(
                this@ConditionalDecorationActivity,
                RecyclerView.VERTICAL,
                false
            )
            adapter = RandomNumberAdapter()


            //Decoration starts here
            addItemDecoration(Polet().apply {
                sizingDecoration = SimpleSpacingDecoration(defaultSpacing)
                drawingDecorations.add(PaintingDecoration(accentColor))
                selectiveDecoration.add(DataConditionSelector { adapter, position ->
                    return@DataConditionSelector (adapter as RandomNumberAdapter?)?.list?.get(position) ?: 0 > 5
                })
            })

            addItemDecoration(Polet().apply {
                sizingDecoration = SimpleSpacingDecoration(defaultSpacing)
                drawingDecorations.add(PaintingDecoration(primaryColor))
                selectiveDecoration.add(DataConditionSelector { adapter, position ->
                    return@DataConditionSelector (adapter as RandomNumberAdapter?)?.list?.get(position) ?: 0 <= 5
                })
            })
        }

    }
}