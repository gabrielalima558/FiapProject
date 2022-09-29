package com.example.scaniaapp.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.scaniaapp.database.FeedbackDao

class FeedbackViewModelFactory(
    private val dataSource: FeedbackDao
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FeedbackListViewModel::class.java)) {
            return FeedbackListViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewHolder class")
    }
}