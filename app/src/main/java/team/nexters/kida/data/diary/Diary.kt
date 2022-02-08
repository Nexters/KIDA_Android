package team.nexters.kida.data.diary

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.util.Date

@Entity
data class Diary(
    @TypeConverters(DateConverter::class)
    val date: Date,
    val title: String,
    val content: String,
    val keyword: String,
    @PrimaryKey val id: Int? = null
)
