package com.example.scaniaapp.vehicleinfo

import androidx.lifecycle.ViewModel
import com.example.scaniaapp.database.Feedback
import com.example.scaniaapp.database.FeedbackDao
import kotlinx.coroutines.*

class VehicleInfoViewModel(val dataSource: FeedbackDao) : ViewModel() {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    fun startInsertFeedback(feedback: Feedback) {
        uiScope.launch {
            insertFeedback(feedback)
        }
    }

    private suspend fun insertFeedback(feedback: Feedback) {
        withContext(Dispatchers.IO) {
            dataSource.insertFeedback(feedback)

        }
    }
}