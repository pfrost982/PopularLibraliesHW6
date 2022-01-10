package ru.geekbrains.data

import io.reactivex.rxjava3.core.Single

interface GitHubUserRepository {

    fun getUsers(): Single<List<GitHubUser>>

    fun getUserByLogin(login: String): Single<GitHubUser>

}