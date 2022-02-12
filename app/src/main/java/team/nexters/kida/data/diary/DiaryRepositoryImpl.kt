package team.nexters.kida.data.diary

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.mapLatest
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

    override suspend fun deleteDiaryById(id: Long) {
        dao.deleteDiaryById(id)
    }

    override suspend fun getDiaryById(id: Long): Diary? {
        return dao.getDiaryById(id)
    }

    override fun getDiary(id: Long): Flow<Diary> {
        return dao.getDiary(id)
    }

    override fun getDiaries(): Flow<List<Diary>> {
        return dao.getDiaries()
    }

    override fun canWriteDiary(): Flow<Boolean> {
        return dao.getDiaryByOverToday()
            .mapLatest { it.isEmpty() }
    }
}
