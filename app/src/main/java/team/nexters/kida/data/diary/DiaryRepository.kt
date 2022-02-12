package team.nexters.kida.data.diary

import kotlinx.coroutines.flow.Flow

interface DiaryRepository {
    suspend fun insertDiary(diary: Diary)
    suspend fun deleteDiary(diary: Diary)
    suspend fun deleteDiaryById(id: Long)
    suspend fun getDiaryById(id: Long): Diary?
    fun getDiary(id: Long): Flow<Diary?>
    fun getDiaries(): Flow<List<Diary>>
    fun canWriteDiary(): Flow<Boolean>
}
