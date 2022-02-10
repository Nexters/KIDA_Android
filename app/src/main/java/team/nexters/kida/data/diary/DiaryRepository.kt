package team.nexters.kida.data.diary

import kotlinx.coroutines.flow.Flow

interface DiaryRepository {
    suspend fun insertDiary(diary: Diary)
    suspend fun deleteDiary(diary: Diary)
    suspend fun getDiaryById(id: Int): Diary?
    fun getDiary(id: Int): Flow<Diary?>
    fun getDiaries(): Flow<List<Diary>>
    fun canWriteDiary(): Flow<Boolean>
}
