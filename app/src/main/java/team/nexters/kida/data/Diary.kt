package team.nexters.kida.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
internal data class Diary(
    val title: String,
    val content: String,
    @PrimaryKey val id: Int? = null
)