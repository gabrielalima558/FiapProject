package com.example.scaniaapp.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.scaniaapp.database.Feedback
import com.example.scaniaapp.database.FeedbackDao
import kotlinx.coroutines.*

class FeedbackDetailViewModel(val dataSource: FeedbackDao): ViewModel() {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var _feedback = MutableLiveData<Feedback>()
    val feedback: LiveData<Feedback> get() = _feedback


    fun startGetFeedback(name: String) {
        uiScope.launch {
            _feedback.value = getFeedback(name)
        }
    }

    private suspend fun getFeedback(name: String): Feedback {
        var feed: Feedback
        withContext(Dispatchers.IO) {
             feed = dataSource.getFeedback(name) as Feedback
        }
        return feed
    }
    fun startUpdateFeedback(feedback: Feedback) {
        uiScope.launch {
            updateFeedback(feedback)
        }
    }

    private suspend fun updateFeedback(feedback: Feedback) {
        withContext(Dispatchers.IO) {
            dataSource.updateItem(feedback)

        }
    }
}