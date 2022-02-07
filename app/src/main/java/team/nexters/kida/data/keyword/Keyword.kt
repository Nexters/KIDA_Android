package team.nexters.kida.data.keyword

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Keyword(
    val name: String
) : Parcelable
