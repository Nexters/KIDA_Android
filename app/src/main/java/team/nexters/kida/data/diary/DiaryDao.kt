package team.nexters.kida.data.diary

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DiaryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDiary(diary: Diary)

    @Delete
    suspend fun deleteDiary(diary: Diary)

    @Query("SELECT * FROM diary WHERE id = :id")
    suspend fun getDiaryById(id: Int): Diary?

    @Query("SELECT * FROM diary WHERE id = :id")
    fun getDiary(id: Int): Flow<Diary>

    @Query("SELECT * FROM diary")
    fun getDiaries(): Flow<List<Diary>>

    @Query("SELECT * FROM diary WHERE strftime('%Y-%m-%d', datetime(latestDate/1000, 'unixepoch')) >= DATE('now')")
    fun getDiaryByOverToday(): Flow<List<Diary>>
}
