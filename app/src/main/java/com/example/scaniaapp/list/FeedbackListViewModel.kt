package com.example.scaniaapp.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.scaniaapp.database.FeedbackDao
import com.example.scaniaapp.list.adapter.FeedbackListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FeedbackListViewModel(dataSource: FeedbackDao): ViewModel() {
    val allFeedbacks: Flow<PagingData<FeedbackListItem.Item>> = Pager(
        config = PagingConfig(
            pageSize = 60,
            enablePlaceholders = true,
            maxSize = 200
        )
    ) {
        dataSource.allTeam()
    }.flow
        .map { pagingData ->
            pagingData
                // Map cheeses to common UI model.
                .map { cheese -> FeedbackListItem.Item(cheese) }
        }
        .cachedIn(viewModelScope)
}