package ru.geekbrains.mvpuser

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.data.retrofit.GitHubApi
import ru.geekbrains.mvpuser.user_room.DBRepoStorage
import javax.inject.Inject

class UserReposRepositoryImpl
@Inject constructor(
    private val gitHubApi: GitHubApi,
    private val roomRepoDB: DBRepoStorage
) : UserReposRepository {

    override fun getUserRepos(login: String): Single<List<GitHubRepo>> {
        return roomRepoDB.getRepoDao().getReposByLogin("$login%")
            .flatMap { repos ->
                repos
                if (repos.isEmpty()) {
                    gitHubApi.fetchUserRepositories(login)
                        .map { repos ->
                            roomRepoDB.getRepoDao().saveRepos(repos)
                            repos
                        }
                } else {
                    Single.just(repos)
                }
            }
    }
}