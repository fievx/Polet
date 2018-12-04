package com.fievx.paoloproject.simpleSpacing

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fievx.paolo.spacingDecoration.DumbSpacingDecoration
import com.fievx.paolo.DecorationComposer
import com.fievx.paoloproject.R
import com.fievx.paoloproject.adapter.AllTextsAdapter

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
            addItemDecoration(DecorationComposer().apply {
                sizingDecoration = DumbSpacingDecoration(resources.getDimensionPixelSize(R.dimen.spacing_default))
            })
        }
    }
}