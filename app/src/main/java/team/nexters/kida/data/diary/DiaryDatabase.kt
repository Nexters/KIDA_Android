package team.nexters.kida.data.diary

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [Diary::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(DateConverter::class)
abstract class DiaryDatabase : RoomDatabase() {
    abstract fun diaryDao(): DiaryDao
}
