package com.reece.gogolookhomework.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.reece.gogolookhomework.R
import com.reece.gogolookhomework.view.result.ResultActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.intentFor

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_search_submit.setOnClickListener {
            val query = main_search_edit.text.toString()
            startActivity(intentFor<ResultActivity>("query" to query))
        }

    }
}
