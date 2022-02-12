package team.nexters.kida.ui.list

sealed class ListEvent {
    class OnEditClick(val diaryId: Long) : ListEvent()
}
