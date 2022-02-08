package team.nexters.kida.ui.popup

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import team.nexters.kida.R
import team.nexters.kida.ui.theme.Theme

@Composable
fun PopupErrorContent(popUpTo: () -> Unit) {
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
                context.getString(R.string.popup_error_info),
                color = Theme.colors.label2,
                style = Theme.typography.h3
            )
            Text(
                text = context.getString(R.string.popup_error_title),
                style = Theme.typography.display,
                color = Theme.colors.textDefault
            )
            Text(
                text = context.getString(R.string.popup_error_content),
                color = Theme.colors.placeholderInactive,
                style = Theme.typography.h4,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.size(30.dp))
        }
    }
}
