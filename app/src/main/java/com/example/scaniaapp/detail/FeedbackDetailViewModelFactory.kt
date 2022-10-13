package com.example.scaniaapp.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.scaniaapp.database.FeedbackDao

class FeedbackDetailViewModelFactory(
    private val dataSource: FeedbackDao
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FeedbackDetailViewModel::class.java)) {
            return FeedbackDetailViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewHolder class")
    }
}