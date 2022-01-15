package team.nexters.kida.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface DiaryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDiary(diary: Diary)

    @Delete
    suspend fun deleteDiary(diary: Diary)

    @Query("SELECT * FROM diary WHERE id = :id")
    suspend fun getDiaryById(id: Int): Diary?

    @Query("SELECT * FROM diary")
    fun getDiaries(): Flow<List<Diary>>
}