package team.nexters.kida.ui.list.dialog

sealed class ListDialogEvent {
    object OnClickDelete : ListDialogEvent()
    object OnClickModify : ListDialogEvent()
}