package team.nexters.kida.ui.detail

sealed class DetailEvent {
    class OnMoreClick(val diaryId: Int) : DetailEvent()
}
