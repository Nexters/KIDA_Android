package team.nexters.kida.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import team.nexters.kida.component.BalloonTailShape
import team.nexters.kida.data.diary.Diary
import team.nexters.kida.ui.theme.Theme
import team.nexters.kida.util.DateUtils
import java.util.*

@Composable
fun DiaryItem(
    diary: Diary,
    onEvent: (ListEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(top = 6.dp, bottom = 6.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Box {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(end = 12.dp)
                    .background(
                        color = Theme.colors.bgLayered2,
                        shape = RoundedCornerShape(
                            topStart = CornerSize(10.dp),
                            topEnd = CornerSize(10.dp),
                            bottomEnd = CornerSize(0.dp),
                            bottomStart = CornerSize(0.dp)
                        )
                    )
                    .padding(all = 20.dp)
                    .align(Alignment.TopStart),
                verticalArrangement = Arrangement.Center,
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "#${diary.keyword}",
                        style = TextStyle(
                            color = Theme.colors.label2,
                            fontSize = 12.sp
                        )
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = DateUtils.listDate(diary.date),
                        style = TextStyle(
                            color = Theme.colors.label,
                            fontSize = 12.sp
                        )
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = diary.title,
                    style = TextStyle(
                        color = Theme.colors.textDefault,
                        fontSize = 16.sp
                    )
                )
            }
            Box(modifier = modifier
                .width(22.dp)
                .height(22.dp)
                .clip(shape = BalloonTailShape())
                .background(Theme.colors.bgLayered2)
                .align(Alignment.CenterEnd)
            )
        }
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(end = 12.dp)
                .background(
                    color = Theme.colors.bgLayered,
                    shape = RoundedCornerShape(
                        topStart = CornerSize(0.dp),
                        topEnd = CornerSize(0.dp),
                        bottomEnd = CornerSize(10.dp),
                        bottomStart = CornerSize(10.dp)
                    )
                )
                .padding(all = 20.dp),
            text = diary.content,
            style = TextStyle(
                color = Theme.colors.textContent,
                fontSize = 14.sp
            )
        )
    }
}

@Preview
@Composable
fun DiaryItemPreview() {
    DiaryItem(
        diary = Diary(title = "hihi", content = "asdf", keyword = "zzzz", date = Date()),
        onEvent = {})
}
