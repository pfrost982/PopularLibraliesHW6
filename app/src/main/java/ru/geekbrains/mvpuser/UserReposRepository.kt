package ru.geekbrains.mvpuser

import io.reactivex.rxjava3.core.Single

interface UserReposRepository {
    fun getUserRepos(login: String): Single<List<GitHubRepo>>
}