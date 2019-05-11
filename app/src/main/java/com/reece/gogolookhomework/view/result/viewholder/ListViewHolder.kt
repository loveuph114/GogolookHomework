package com.reece.gogolookhomework.view.result.viewholder

import android.view.View
import com.bumptech.glide.Glide
import com.reece.gogolookhomework.model.Model
import kotlinx.android.synthetic.main.item_result_list.view.*

class ListViewHolder(itemView: View) : ResultViewHolder(itemView) {
    override fun bind(result: Model.Hit) {
        itemView.result_list_tag.text = "tags: ${result.tags}"
        itemView.result_list_comments.text = "comments: ${result.comments}"
        itemView.result_list_downloads.text = "downloads: ${result.downloads}"
        itemView.result_list_favorites.text = "favorites: ${result.favorites}"
        itemView.result_list_likes.text = "likes: ${result.likes}"
        itemView.result_list_views.text = "views: ${result.views}"

        Glide.with(itemView).load(result.previewURL).centerCrop().into(itemView.result_list_photo)
    }
}