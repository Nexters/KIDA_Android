package team.nexters.kida.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import team.nexters.kida.data.DiaryRepository
import team.nexters.kida.data.DiaryRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface AppModule {
    @Binds
    @Singleton
    fun bindDiaryRepository(repository: DiaryRepositoryImpl): DiaryRepository
}