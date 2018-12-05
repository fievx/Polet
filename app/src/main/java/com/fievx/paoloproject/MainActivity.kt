package com.fievx.paoloproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.fievx.paoloproject.conditionnalDecoration.ConditionalDecorationActivity
import com.fievx.paoloproject.simpleSpacing.SimpleSpacingDecorationActivity
import com.fievx.paoloproject.simpleSpacingWithLine.SimpleSpacingWithLineDecorationActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.bt_simple_spacing).setOnClickListener {
            startActivity(Intent(this, SimpleSpacingDecorationActivity::class.java))
        }

        findViewById<Button>(R.id.bt_simple_spacing_divided).setOnClickListener {
            startActivity(Intent(this, SimpleSpacingWithLineDecorationActivity::class.java))
        }

        findViewById<Button>(R.id.bt_conditional_decoration).setOnClickListener {
            startActivity(Intent(this, ConditionalDecorationActivity::class.java))
        }
    }
}
