package team.nexters.kida.ui.keyword

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import team.nexters.kida.R

@Serializable
@Parcelize
enum class KeywordCard(val resId: Int) : Parcelable {
    ONE(R.drawable.card1),
    TWO(R.drawable.card2),
    THREE(R.drawable.card3),
    FOUR(R.drawable.card4),
    FIVE(R.drawable.card5),
    SIX(R.drawable.card6);
}
