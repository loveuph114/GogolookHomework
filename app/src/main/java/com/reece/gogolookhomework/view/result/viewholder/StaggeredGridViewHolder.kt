package com.reece.gogolookhomework.view.result.viewholder

import android.view.View
import com.bumptech.glide.Glide
import com.reece.gogolookhomework.model.Model
import kotlinx.android.synthetic.main.item_result_staggered_grid.view.*

class StaggeredGridViewHolder(itemView: View) : ResultViewHolder(itemView) {

    override fun bind(result: Model.Hit) {
        itemView.result_staggered_tag.text = "tags: ${result.tags}"
        itemView.result_staggered_comments.text = "comments: ${result.comments}"
        itemView.result_staggered_downloads.text = "downloads: ${result.downloads}"
        itemView.result_staggered_favorites.text = "favorites: ${result.favorites}"
        itemView.result_staggered_likes.text = "likes: ${result.likes}"
        itemView.result_staggered_views.text = "views: ${result.views}"

        val photo = itemView.result_staggered_photo
        photo.setRatio(result.previewWidth.toFloat()/result.previewHeight.toFloat())

        Glide.with(itemView)
            .load(result.previewURL)
            .into(photo)
    }
}