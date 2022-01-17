package ru.geekbrains.data

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.mvpuser.GitHubRepo

interface GitHubUserRepository {

    fun getUsers(): Single<List<GitHubUser>>

    fun getUserByLogin(login: String): Single<GitHubUser>

    fun getUserRepos(login: String): Single<List<GitHubRepo>>
}