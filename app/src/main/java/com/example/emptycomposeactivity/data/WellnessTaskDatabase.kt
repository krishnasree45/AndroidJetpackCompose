package com.example.emptycomposeactivity.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.emptycomposeactivity.model.WellnessTask


@Database(
    entities = [WellnessTask::class],
    version = 2,
    exportSchema = true
)
abstract class WellnessTaskDatabase : RoomDatabase() {
    abstract fun wellnessTaskDao(): WellnessTaskDao

    companion object {
        @Volatile
        private var INSTANCE: WellnessTaskDatabase? = null

        fun getDatabase(context: Context): WellnessTaskDatabase {
            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    WellnessTaskDatabase::class.java,
                    "wellnesstask_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}