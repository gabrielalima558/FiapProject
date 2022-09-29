package com.example.scaniaapp.list.adapter

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.scaniaapp.R
import com.example.scaniaapp.database.Feedback


class FeedbackViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
) {
    var feedback: Feedback? = null
        private set
    private val nameView = itemView.findViewById<TextView>(R.id.name)
    private val description = itemView.findViewById<TextView>(R.id.description)

    fun bindTo(item: FeedbackListItem?) {
        if (item is FeedbackListItem.Separator) {
            nameView.text = item.name
            description.text = item.description
            nameView.setTypeface(null, Typeface.BOLD)
            description.setTypeface(null, Typeface.BOLD)
        } else {
            nameView.text = item?.name
            description.text = item?.description
            nameView.setTypeface(null, Typeface.NORMAL)
            description.setTypeface(null, Typeface.NORMAL)
        }
        feedback = (item as? FeedbackListItem.Item)?.feedback
        nameView.text = item?.name
        description.text = item?.description
    }
}