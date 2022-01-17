package ru.geekbrains.mvpuser.user_room

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.geekbrains.mvpuser.GitHubRepo

@Database(exportSchema = false, entities = [GitHubRepo::class], version = 1)
abstract class DBRepoStorage : RoomDatabase() {

    abstract fun getRepoDao(): GitHubRepoDao
}