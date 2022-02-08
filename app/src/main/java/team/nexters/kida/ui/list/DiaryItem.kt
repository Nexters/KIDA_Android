package team.nexters.kida.ui.list

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import team.nexters.kida.data.diary.Diary
import team.nexters.kida.ui.theme.Theme
import java.util.Date

@Composable
fun DiaryItem(
    diary: Diary,
    onEvent: (ListEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(10.dp),
        color = Theme.colors.bgLayered,
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
                            color = Theme.colors.textDefault,
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
                            color = Theme.colors.btnActive,
                            fontSize = 12.sp
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = diary.keyword,
                        style = TextStyle(
                            color = Theme.colors.textContent,
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
                            color = Theme.colors.textContent
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun DiaryItemPreview() {
    DiaryItem(diary = Diary(title = "hihi", content = "asdf", keyword = "zzzz", date = Date()), onEvent = {})
}
