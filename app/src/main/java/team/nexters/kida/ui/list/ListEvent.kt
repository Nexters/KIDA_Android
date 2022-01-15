package team.nexters.kida.ui.list

import team.nexters.kida.data.diary.Diary

sealed class ListEvent {
    class OnDiaryClick(val diary: Diary) : ListEvent()
    object OnWriteClick : ListEvent()
}