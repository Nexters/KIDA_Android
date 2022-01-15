package team.nexters.kida.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Diary::class],
    version = 1,
    exportSchema = true
)
internal abstract class DiaryDatabase : RoomDatabase() {
    abstract fun diaryDao(): DiaryDao
}