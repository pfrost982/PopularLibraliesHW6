package ru.geekbrains.mvpuser.di


import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.geekbrains.data.retrofit.GitHubApi
import ru.geekbrains.data.room.DBStorage
import ru.geekbrains.mvpuser.UserReposRepository
import ru.geekbrains.mvpuser.UserReposRepositoryImpl
import ru.geekbrains.mvpuser.user_room.DBRepoStorage
import javax.inject.Named


@Module
class AllDependenciesModule {

    @FragmentScope
    @Provides
    fun providesRoomModule(context: Context): DBRepoStorage {
        return Room.databaseBuilder(context, DBRepoStorage::class.java, "repos.db")
            .build()
    }

    @FragmentScope
    @Provides
    fun providesUserReposRepositoryModule(
        @Named("prod") api: GitHubApi,
        roomDB: DBStorage,
        roomRepoDB: DBRepoStorage
    ): UserReposRepository {
        return UserReposRepositoryImpl(api, roomDB, roomRepoDB)
    }
}