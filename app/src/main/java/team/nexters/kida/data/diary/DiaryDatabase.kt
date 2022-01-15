package team.nexters.kida.data.diary

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Diary::class],
    version = 1,
    exportSchema = true
)
abstract class DiaryDatabase : RoomDatabase() {
    abstract fun diaryDao(): DiaryDao
}
