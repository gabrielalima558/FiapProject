package com.example.scaniaapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Feedback::class], version = 1, exportSchema = false)
abstract class FeedbackDatabase : RoomDatabase() {
    abstract val feedbackDao: FeedbackDao

    companion object {
        @Volatile
        private var INSTANCE: FeedbackDatabase? = null
        fun getInstace(context: Context): FeedbackDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FeedbackDatabase::class.java,
                        "feedback_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                }
                return instance
            }
        }
    }
}
