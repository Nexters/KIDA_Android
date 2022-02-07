package team.nexters.kida.ui.popup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 20.dp, end = 20.dp),
            onClick = popUpTo
        ) {
            Image(painter = painterResource(R.drawable.ic_closed), contentDescription = "closed")
        }

        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.size(100.dp))

            Text(
                text = context.getString(R.string.popup_info_title),
                color = Theme.colors.textDefault,
                style = Theme.typography.display,
                textAlign = TextAlign.Center
            )
            Text(
                text = context.getString(R.string.popup_info_content),
                color = Theme.colors.placeholderInactive,
                style = Theme.typography.h4,
                textAlign = TextAlign.Center
            )

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
