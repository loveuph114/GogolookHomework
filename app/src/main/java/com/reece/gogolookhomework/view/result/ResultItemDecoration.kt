package com.reece.gogolookhomework.view.result

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.reece.gogolookhomework.dipToPixels

class ResultItemDecoration : RecyclerView.ItemDecoration() {

    var listMode: Int = ResultAdapter.LIST_MODE_LIST

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        val space = view.dipToPixels(8f).toInt()

        when (listMode) {
            ResultAdapter.LIST_MODE_LIST -> {
                if (parent.getChildAdapterPosition(view) == 0) {
                    outRect.top = space
                }

                outRect.left = space
                outRect.right = space
                outRect.bottom = space
            }

            ResultAdapter.LIST_MODE_GRID -> {
                val position = parent.getChildAdapterPosition(view)

                if (position == 0 || position == 1) {
                    outRect.top = space
                }

                if (position % 2 == 0) {
                    outRect.left = space
                    outRect.right = space / 2
                } else {
                    outRect.left = space / 2
                    outRect.right = space
                }

                outRect.bottom = space
            }

            ResultAdapter.LIST_MODE_STAGGERED_GRID -> {
                val lm = parent.layoutManager as StaggeredGridLayoutManager
                val lp = view.layoutParams as StaggeredGridLayoutManager.LayoutParams

                val spanCount = lm.spanCount
                val spanIndex = lp.spanIndex
                val position = parent.getChildAdapterPosition(view)

                if (spanIndex == 0) {
                    outRect.left = space
                }

                if (position < spanCount) {
                    outRect.top = space
                }

                outRect.right = space
                outRect.bottom = space
            }
        }
    }


}
