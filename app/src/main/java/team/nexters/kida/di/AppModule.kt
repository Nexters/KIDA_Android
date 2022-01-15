package team.nexters.kida.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import team.nexters.kida.data.diary.DiaryRepository
import team.nexters.kida.data.diary.DiaryRepositoryImpl
import team.nexters.kida.data.keyword.KeywordRepository
import team.nexters.kida.data.keyword.KeywordRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface AppModule {
    @Binds
    @Singleton
    fun bindDiaryRepository(repository: DiaryRepositoryImpl): DiaryRepository

    @Binds
    @Singleton
    fun bindKeywordRepository(repository: KeywordRepositoryImpl): KeywordRepository
}