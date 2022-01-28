package team.nexters.kida.util

import android.text.format.DateFormat
import java.util.Date

object DateUtils {

    fun today(): String {
        return DateFormat.format("M.dd(EE)", Date()).toString()
    }
}
