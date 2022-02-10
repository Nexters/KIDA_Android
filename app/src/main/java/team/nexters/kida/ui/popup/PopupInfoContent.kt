package team.nexters.kida.ui.popup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import team.nexters.kida.R
import team.nexters.kida.ui.theme.Theme

@Composable
fun PopupInfoContent(
    popUpTo: () -> Unit
) {

    val context = LocalContext.current

    Box(
        modifier = Modifier.background(
            color = Theme.colors.background,
            shape = RoundedCornerShape(10.dp)
        )
    ) {

        IconButton(
            modifier = Modifier.align(Alignment.TopEnd),
            onClick = popUpTo
        ) {
            Image(painter = painterResource(R.drawable.ic_closed), contentDescription = "closed")
        }

        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.size(30.dp))

            PopupInfoGraph(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .height(138.dp)
            )

            Spacer(modifier = Modifier.size(36.dp))

            Text(
                modifier = Modifier.padding(horizontal = 59.dp),
                text = context.getString(R.string.popup_info_title),
                color = Theme.colors.textDefault,
                style = Theme.typography.display,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.size(14.dp))
            Text(
                text = context.getString(R.string.popup_info_content),
                color = Theme.colors.placeholderInactive,
                style = Theme.typography.h4,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.size(26.dp))

            Button(
                onClick = popUpTo,
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Theme.colors.btnActive,
                    contentColor = Color.White
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 56.dp)
            ) {
                Text(text = context.getString(R.string.popup_info_btn), fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.size(28.dp))
        }
    }
}

@Composable
fun PopupInfoGraph(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Image(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(id = R.drawable.popup_info),
            contentDescription = null
        )

        Image(
            modifier = Modifier
                .size(width = 71.dp, height = 33.dp)
                .align(Alignment.BottomCenter)
                .offset(y = 8.dp),
            painter = painterResource(id = R.drawable.popup_info_logo),
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun PopupInfoGraphPreview() {
    PopupInfoGraph()
}

@Preview
@Composable
fun PopupInfoPreview() {
    PopupInfoContent {}
}
