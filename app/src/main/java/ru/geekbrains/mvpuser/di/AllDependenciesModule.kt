package ru.geekbrains.mvpuser.di


import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.geekbrains.mvpuser.user_room.DBRepoStorage


@Module
class AllDependenciesModule {

    @FragmentScope
    @Provides
    fun providesRoomModule(context: Context): DBRepoStorage {
        return Room.databaseBuilder(context, DBRepoStorage::class.java, "repos.db")
            .build()
    }

}