package team.nexters.kida.ui.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import team.nexters.kida.data.diary.Diary
import team.nexters.kida.ui.theme.Black
import team.nexters.kida.ui.theme.DarkGrey
import team.nexters.kida.ui.theme.Disable
import team.nexters.kida.ui.theme.Primary
import team.nexters.kida.ui.theme.White

@Composable
fun DiaryItem(
    diary: Diary,
    onEvent: (ListEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .clickable { onEvent(ListEvent.OnDiaryClick(diary)) }
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(10.dp),
        color = White,
        elevation = 4.dp,
    ) {
        Row(
            modifier = modifier.padding(all = 20.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(
                modifier = modifier
                    .weight(1F)
                    .padding(top = 6.dp),
                verticalArrangement = Arrangement.Center,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = diary.title,
                        style = TextStyle(
                            color = Black,
                            fontSize = 18.sp
                        )
                    )
                }
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "#${diary.keyword}",
                        style = TextStyle(
                            color = Primary,
                            fontSize = 12.sp
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = diary.keyword,
                        style = TextStyle(
                            color = Disable,
                            fontSize = 12.sp
                        )
                    )
                }
                Spacer(modifier = Modifier.height(17.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = diary.content,
                        style = TextStyle(
                            color = DarkGrey
                        )
                    )
                }
            }
        }
    }
}
