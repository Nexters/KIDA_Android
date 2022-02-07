package team.nexters.kida.ui.keyword

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.rememberInsetsPaddingValues
import team.nexters.kida.R
import team.nexters.kida.component.CenterAppBar
import team.nexters.kida.data.keyword.Keyword
import team.nexters.kida.ui.theme.Theme
import team.nexters.kida.util.DateUtils

@Composable
fun KeywordConfirmScreen(
    keyword: Keyword,
    card: KeywordCard,
    upPress: () -> Unit,
    onConfirm: (Keyword) -> Unit,
    onInfoClick: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = Theme.colors.background,
        topBar = {
            CenterAppBar(
                title = { Text(text = DateUtils.today()) },
                backgroundColor = Theme.colors.background,
                contentPadding = rememberInsetsPaddingValues(
                    insets = LocalWindowInsets.current.statusBars,
                    applyBottom = false,
                ),
                navigationIcon = {
                    IconButton(onClick = { upPress() }) {
                        Icon(Icons.Default.ArrowBack, null)
                    }
                },
                actions = {
                    IconButton(onClick = onInfoClick) {
                        Image(
                            painter = painterResource(R.drawable.ic_info),
                            contentDescription = null,
                        )
                    }
                }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding()
        ) {

            // bottom content
            KeywordConfirmBottomContent(
                modifier = Modifier.align(Alignment.BottomCenter),
                keyword = keyword,
                onRetryClick = upPress,
                onConfirmClick = onConfirm
            )

            Image(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .fillMaxWidth()
                    .fillMaxHeight(0.568f)
                    .padding(top = 20.dp, start = 67.dp, end = 67.dp),
                painter = painterResource(id = card.resId),
                contentDescription = null
            )
        }
    }
}

@Composable
fun KeywordConfirmBottomContent(
    modifier: Modifier = Modifier,
    keyword: Keyword,
    onRetryClick: () -> Unit,
    onConfirmClick: (Keyword) -> Unit
) {
    val context = LocalContext.current
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Theme.colors.bgLayered,
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
            )
    ) {
        // TODO particle

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(106.dp))

            Text(
                text = context.getString(R.string.keyword_confirm_title),
                fontSize = 12.sp,
                color = Theme.colors.label2
            )

            Spacer(modifier = Modifier.size(8.dp))

            // TODO font padding
            Text(
                modifier = Modifier
                    .background(
                        color = Theme.colors.bgLayered2,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(horizontal = 20.dp),
                text = keyword.name,
                fontSize = 24.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.size(36.dp))

            Button(
                onClick = { onConfirmClick(keyword) },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Theme.colors.btnActive,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 20.dp)
            ) {
                Text(text = context.getString(R.string.keyword_confirm_submit), fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.size(12.dp))

            Text(
                text = context.getString(R.string.keyword_confirm_retry),
                style = TextStyle(
                    color = Theme.colors.btnRe,
                    textDecoration = TextDecoration.Underline,
                    fontSize = 14.sp
                ),
                modifier = Modifier
                    .padding(5.dp)
                    .clickable { onRetryClick() }
            )

            Spacer(modifier = Modifier.size(30.dp))
        }
    }
}

@Preview
@Composable
fun KeywordConfirmBottomContentPreview() {
    KeywordConfirmBottomContent(keyword = Keyword("가을"), onRetryClick = { }, onConfirmClick = {})
}
