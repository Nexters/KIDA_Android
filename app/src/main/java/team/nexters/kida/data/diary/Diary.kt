package team.nexters.kida.data.diary

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Diary(
    val title: String,
    val content: String,
    val keyword: String,
    @PrimaryKey val id: Int? = null
)
