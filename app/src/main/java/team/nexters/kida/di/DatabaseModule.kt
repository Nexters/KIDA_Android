package team.nexters.kida.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import team.nexters.kida.data.DiaryDao
import team.nexters.kida.data.DiaryDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class DatabaseModule {
    @Provides
    @Singleton
    fun provideDiaryDataBase(app: Application): DiaryDatabase {
        return Room.databaseBuilder(
            app,
            DiaryDatabase::class.java,
            "diary_db"
        ).build()
    }

    @Provides
    fun provideDiaryDao(database: DiaryDatabase): DiaryDao {
        return database.diaryDao()
    }
}