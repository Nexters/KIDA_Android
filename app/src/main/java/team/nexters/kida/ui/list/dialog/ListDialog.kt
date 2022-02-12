package team.nexters.kida.ui.list.dialog

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import team.nexters.kida.util.UiEvent

@Composable
fun ListDialog(
    viewModel: ListDialogViewModel = hiltViewModel(),
    onNavigate: (UiEvent.Navigate) -> Unit,
    onPopBackStack: () -> Unit
) {
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)
                is UiEvent.PopBackStack -> onPopBackStack()
                else -> {
                }
            }
        }
    }

    AlertDialog(
        onDismissRequest = { },
        title = { },
        text = {
            Text(text = "가즈아")
        },
        confirmButton = {
            TextButton(
                onClick = {
                    viewModel.onEvent(ListDialogEvent.OnClickDelete)
                }
            ) {
                Text(text = "삭제하기")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    viewModel.onEvent(ListDialogEvent.OnClickModify)
                }
            ) {
                Text(text = "수정하기")
            }
        }
    )
}