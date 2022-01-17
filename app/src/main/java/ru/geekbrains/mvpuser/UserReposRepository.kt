package ru.geekbrains.mvpuser

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.data.GitHubUser

interface UserReposRepository {
    fun getUserByLogin(login: String): Single<GitHubUser>

    fun getUserRepos(login: String): Single<List<GitHubRepo>>
}