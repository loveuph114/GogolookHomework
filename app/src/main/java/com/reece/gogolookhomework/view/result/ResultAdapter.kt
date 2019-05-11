package com.reece.gogolookhomework.view.result

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.reece.gogolookhomework.R
import com.reece.gogolookhomework.model.Model
import com.reece.gogolookhomework.view.result.viewholder.GridViewHolder
import com.reece.gogolookhomework.view.result.viewholder.ListViewHolder
import com.reece.gogolookhomework.view.result.viewholder.ResultViewHolder
import com.reece.gogolookhomework.view.result.viewholder.StaggeredGridViewHolder

class ResultAdapter : RecyclerView.Adapter<ResultViewHolder>() {

    companion object {
        const val LIST_MODE_LIST = 0
        const val LIST_MODE_GRID = 1
        const val LIST_MODE_STAGGERED_GRID = 2
    }

    var listMode : Int = LIST_MODE_LIST
    val data = arrayListOf<Model.Hit>()

    override fun getItemViewType(position: Int): Int {
        return listMode
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        when(viewType) {
            LIST_MODE_LIST -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_result_list, parent, false)
                return ListViewHolder(view)
            }

            LIST_MODE_GRID -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_result_grid, parent, false)
                return GridViewHolder(view)
            }

            LIST_MODE_STAGGERED_GRID -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_result_staggered_grid, parent, false)
                return StaggeredGridViewHolder(view)
            }

            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_result_list, parent, false)
                return ListViewHolder(view)
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(viewHolder: ResultViewHolder, position: Int) {
        viewHolder.bind(data[position])
    }


}