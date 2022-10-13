package com.example.scaniaapp.list.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.scaniaapp.database.Feedback

class FeedbackAdapter : PagingDataAdapter<FeedbackListItem, FeedbackViewHolder>(diffCallback) {
    private lateinit var mListener: OnItemClickListener

    interface OnItemClickListener {
        fun OnItemclick(position: Int, feedback: Feedback?)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        mListener = listener
    }

    override fun onBindViewHolder(holder: FeedbackViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedbackViewHolder {
        return FeedbackViewHolder(parent, mListener)
    }

    companion object {

        val diffCallback = object : DiffUtil.ItemCallback<FeedbackListItem>() {
            override fun areItemsTheSame(
                oldItem: FeedbackListItem,
                newItem: FeedbackListItem
            ): Boolean {
                return if (oldItem is FeedbackListItem.Item && newItem is FeedbackListItem.Item) {
                    oldItem.feedback.id == newItem.feedback.id
                } else if (oldItem is FeedbackListItem.Separator && newItem is FeedbackListItem.Separator) {
                    oldItem.name == newItem.name
                } else {
                    oldItem == newItem
                }
            }


            override fun areContentsTheSame(
                oldItem: FeedbackListItem,
                newItem: FeedbackListItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}