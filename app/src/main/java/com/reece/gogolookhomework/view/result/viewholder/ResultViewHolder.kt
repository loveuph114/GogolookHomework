package com.reece.gogolookhomework.view.result.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.reece.gogolookhomework.model.Model

abstract class ResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(result: Model.Hit)
}