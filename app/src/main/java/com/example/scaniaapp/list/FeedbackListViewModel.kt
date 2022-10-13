package com.example.scaniaapp.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.scaniaapp.database.Feedback
import com.example.scaniaapp.database.FeedbackDao
import com.example.scaniaapp.list.adapter.FeedbackListItem
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FeedbackListViewModel(val dataSource: FeedbackDao) : ViewModel() {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val list: Flow<PagingData<FeedbackListItem.Item>> = Pager(
        config = PagingConfig(
            pageSize = 60,
            enablePlaceholders = true,
            maxSize = 200
        )
    ) {
        dataSource.allFeedback()
    }.flow
        .map { pagingData ->
            pagingData
                .map { cheese -> FeedbackListItem.Item(cheese) }
        }
        .cachedIn(viewModelScope)

    fun startDeleteFeedback(feedback: Feedback) {
        uiScope.launch {
            deleteFeedback(feedback)
        }
    }

    private suspend fun deleteFeedback(feedback: Feedback) {
        withContext(Dispatchers.IO) {
            dataSource.deleteItem(feedback)

        }
    }
}