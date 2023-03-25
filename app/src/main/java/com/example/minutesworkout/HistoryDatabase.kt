package com.example.minutesworkout

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities =[HistoryEntity::class], version = 1)

abstract  class HistoryDatabase:RoomDatabase() {

	abstract class historyDao():HistoryDao

	companion object {

		@Volatile
		private var INSTANCE: HistoryDatabase? = null


		fun getInstance(context: Context): HistoryDatabase {

			synchronized(this) {
				var instance = INSTANCE

				// If instance is `null` make a new database instance.
				if (instance == null) {
					instance = Room.databaseBuilder(
						context.applicationContext,
						HistoryDatabase::class.java,
						"history_database"
					)
						.fallbackToDestructiveMigration()
						.build()
					// Assign INSTANCE to the newly created database.
					INSTANCE = instance
				}

				// Return instance; smart cast to be non-null.
				return instance
			}
		}
	}

}