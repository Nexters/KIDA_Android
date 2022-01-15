package team.nexters.kida.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface
import androidx.compose.material.Text
import dagger.hilt.android.AndroidEntryPoint
import team.nexters.kida.ui.theme.Theme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Theme {
                Surface {
                    Text(text = "hello")
                }
            }
        }
    }
}
