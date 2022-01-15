package team.nexters.kida.data.diary

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DiaryRepositoryImpl @Inject constructor(
    private val dao: DiaryDao
) : DiaryRepository {

    override suspend fun insertDiary(diary: Diary) {
        dao.insertDiary(diary)
    }

    override suspend fun deleteDiary(diary: Diary) {
        dao.deleteDiary(diary)
    }

    override suspend fun getDiaryById(id: Int): Diary? {
        return dao.getDiaryById(id)
    }

    override fun getDiary(id: Int): Flow<Diary> {
        return dao.getDiary(id)
    }

    override fun getDiaries(): Flow<List<Diary>> {
        return dao.getDiaries()
    }
}