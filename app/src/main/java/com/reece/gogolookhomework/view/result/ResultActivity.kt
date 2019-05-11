package com.reece.gogolookhomework.view.result

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.MenuItem
import android.widget.Button
import android.widget.ProgressBar
import com.reece.gogolookhomework.R
import com.reece.gogolookhomework.hide
import com.reece.gogolookhomework.model.Repository
import com.reece.gogolookhomework.show
import kotlinx.android.synthetic.main.activity_result.*
import kotlinx.coroutines.*

class ResultActivity : AppCompatActivity() {

    private val resultScope: CoroutineScope by lazy {
        CoroutineScope(Dispatchers.Main + Job())
    }

    private val resultItemDecoration = ResultItemDecoration()
    private val resultAdapter = ResultAdapter()
    private lateinit var recyclerview: RecyclerView

    private lateinit var modeBtn : Button
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val query = intent?.extras?.getString("query") ?: ""

        supportActionBar?.apply {
            title = "$query-搜尋結果"
            setDisplayHomeAsUpEnabled(true)
        }

        modeBtn = result_switch_mode_btn
        modeBtn.setOnClickListener {

            when(modeBtn.text) {
                "LIST" -> {
                    setListMode(ResultAdapter.LIST_MODE_GRID)
                }

                "GRID" -> {
                    setListMode(ResultAdapter.LIST_MODE_STAGGERED_GRID)
                }

                "STAGGERED" -> {
                    setListMode(ResultAdapter.LIST_MODE_LIST)
                }
            }

        }

        recyclerview = result_recyclerview
        recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(resultItemDecoration)
            adapter = resultAdapter
        }

        setListMode(ResultAdapter.LIST_MODE_LIST)

        progressBar = result_progressbar
        progressBar.show()

        resultScope.launch {
            val result = Repository.search(
                key = getString(R.string.pixabay_api_key),
                q = query,
                imageType = "photo"
            )

            progressBar.hide()
            result?.apply {
                resultAdapter.data.addAll(this.hits)
                resultAdapter.notifyDataSetChanged()

                result_total.text = "total: $total"
                result_total_hits.text = "total hits: $totalHits"
            }

        }
    }

    private fun setListMode(mode: Int) {
        when (mode) {
            ResultAdapter.LIST_MODE_LIST -> {
                recyclerview.layoutManager = LinearLayoutManager(this)
                modeBtn.text = "LIST"
            }

            ResultAdapter.LIST_MODE_GRID -> {
                recyclerview.layoutManager = GridLayoutManager(this,2)
                modeBtn.text = "GRID"
            }

            ResultAdapter.LIST_MODE_STAGGERED_GRID -> {
                recyclerview.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                modeBtn.text = "STAGGERED"
            }
        }

        resultItemDecoration.listMode = mode
        resultAdapter.listMode = mode
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item?.itemId == android.R.id.home) {
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        resultScope.cancel()
    }
}