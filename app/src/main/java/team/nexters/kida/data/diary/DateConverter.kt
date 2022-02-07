package team.nexters.kida.data.diary

import androidx.room.TypeConverter
import java.util.Date

class DateConverter {
    @TypeConverter
    fun toDate(value: String): Date {
        val date = Date()
        date.time = value.toLong()
        return date
    }

    @TypeConverter
    fun fromDate(date: Date): String {
        return date.time.toString()
    }
}
