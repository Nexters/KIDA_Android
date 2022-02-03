package team.nexters.kida.ui.keyword

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.MaterialTheme
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.ui.TopAppBar
import team.nexters.kida.R
import team.nexters.kida.ui.theme.Theme
import team.nexters.kida.util.DateUtils

@Composable
fun KeywordConfirmScreen(
    keyword: String,
    upPress: () -> Unit,
    onConfirm: (String) -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = { Text(text = DateUtils.today()) },
                backgroundColor = MaterialTheme.colors.background,
                contentPadding = rememberInsetsPaddingValues(
                    insets = LocalWindowInsets.current.statusBars,
                    applyBottom = false,
                ),
                navigationIcon = {
                    IconButton(onClick = { upPress() }) {
                        Icon(Icons.Default.ArrowBack, null)
                    }
                }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(26.dp))
            Text(
                text = context.getString(R.string.keyword_confirm_title),
                style = TextStyle(
                    color = Theme.colors.darkGray,
                    fontSize = 16.sp
                )
            )
            Spacer(modifier = Modifier.size(12.dp))
            Text(
                text = keyword,
                style = TextStyle(
                    color = Theme.colors.primary,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold
                )
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .background(Theme.colors.primary)
            )

            Spacer(modifier = Modifier.size(31.dp))

            Button(
                onClick = { onConfirm(keyword) },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Black,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(start = 55.dp, end = 55.dp)
            ) {
                Text(text = context.getString(R.string.keyword_confirm_submit), fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.size(9.dp))

            Text(
                text = context.getString(R.string.keyword_confirm_retry),
                style = TextStyle(
                    color = Theme.colors.darkGray,
                    textDecoration = TextDecoration.Underline,
                    fontSize = 14.sp
                ),
                modifier = Modifier
                    .padding(5.dp)
                    .clickable { upPress() }
            )
            Spacer(modifier = Modifier.size(35.dp))
        }
    }
}
