package ru.geekbrains.mvpuser.user_room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import io.reactivex.rxjava3.core.Single
import ru.geekbrains.mvpuser.GitHubRepo

@Dao
interface GitHubRepoDao {
    @Query("SELECT * FROM GitHubRepoTable WHERE full_name LIKE :login")
    fun getReposByLogin(login: String): Single<List<GitHubRepo>>

    @Insert(onConflict = REPLACE)
    fun saveRepos(repos: List<GitHubRepo>)
}