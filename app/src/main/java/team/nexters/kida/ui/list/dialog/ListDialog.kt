package team.nexters.kida.ui.list.dialog

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import team.nexters.kida.R
import team.nexters.kida.ui.theme.Theme
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
        backgroundColor = Theme.colors.bgLayered2,
        onDismissRequest = { },
        title = {
            Text(
                text = stringResource(R.string.list_dialog_title)
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    viewModel.onEvent(ListDialogEvent.OnClickDelete)
                }
            ) {
                Text(
                    text = stringResource(R.string.list_dialiog_delete),
                    color = Theme.colors.btnActive
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    viewModel.onEvent(ListDialogEvent.OnClickModify)
                }
            ) {
                Text(
                    text = stringResource(R.string.list_dialiog_modify),
                    color = Theme.colors.btnActive
                )
            }
        }
    )
}