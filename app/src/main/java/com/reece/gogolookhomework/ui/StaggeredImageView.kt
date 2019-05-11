package com.reece.gogolookhomework.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView

class StaggeredImageView : ImageView{

    private var ratio: Float = 0f

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    fun setRatio(aspectRatio: Float) {
        ratio = aspectRatio
        requestLayout()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val measuredWidth = measuredWidth
        setMeasuredDimension(measuredWidth, (measuredWidth / ratio).toInt())
    }
}