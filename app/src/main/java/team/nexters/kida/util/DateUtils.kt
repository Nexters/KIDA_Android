package team.nexters.kida.util

import android.text.format.DateFormat
import java.util.Date

object DateUtils {

    fun today(): String {
        return DateFormat.format("M.dd(EE)", Date()).toString()
    }

    fun todayDate(date: Date): String {
        return DateFormat.format("M.dd(EE)", date).toString()
    }

    fun listDate(date: Date): String {
        return DateFormat.format("yyyy.mm.dd", date).toString()
    }
}
