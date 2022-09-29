package com.example.scaniaapp.database

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface FeedbackDao {
    @Insert
    fun insertFeedback(feedback: Feedback)

    @Query("SELECT * FROM feedback_table ORDER BY person_name COLLATE NOCASE ASC")
    fun allTeam(): PagingSource<Int, Feedback>

}