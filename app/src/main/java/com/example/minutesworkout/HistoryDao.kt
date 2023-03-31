import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.minutesworkout.HistoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {

	@Insert
	suspend fun insert(historyEntity: HistoryEntity)

	@Query("Select * from `history-table`")
	suspend fun fetchAllDates(): Flow<List<HistoryEntity>>
}


