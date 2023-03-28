package com.example.minutesworkout


@Dao
interface HistoryDao {

	@Insert
	suspend fun insert(historyEntity: HistoryEntity)

	@Query("Select * from `history-table`")
	suspend fun fetchAllDates(): Flow<List<HistoryEntity>>
}