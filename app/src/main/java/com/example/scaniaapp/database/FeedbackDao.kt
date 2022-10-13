package com.example.scaniaapp.database

import androidx.paging.PagingSource
import androidx.room.*

@Dao
interface FeedbackDao {
    @Insert
    fun insertFeedback(feedback: Feedback)

    @Query("SELECT * FROM feedback_table ORDER BY person_name COLLATE NOCASE ASC")
    fun allFeedback(): PagingSource<Int, Feedback>

    @Query("SELECT * FROM feedback_table WHERE person_name = :personName")
    fun getFeedback(personName: String?): Feedback?

    @Delete
    fun deleteItem(feedback: Feedback)

    @Update
    fun updateItem(feedback: Feedback)
}