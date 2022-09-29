package com.example.scaniaapp.list.adapter

import com.example.scaniaapp.database.Feedback


sealed class FeedbackListItem(val name: String, val description: String) {
    data class Item(val feedback: Feedback) : FeedbackListItem(feedback.personName, feedback.description)
    data class Separator(private val letter: Char) : FeedbackListItem(letter.toUpperCase().toString(), letter.toUpperCase().toString())
}