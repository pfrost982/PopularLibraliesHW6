package ru.geekbrains.mvpuser

import io.reactivex.rxjava3.core.Single
import ru.geekbrains.data.GitHubUser
import ru.geekbrains.data.retrofit.GitHubApi
import ru.geekbrains.data.room.DBStorage
import ru.geekbrains.mvpuser.user_room.DBRepoStorage
import javax.inject.Inject

class UserReposRepositoryImpl
@Inject constructor(
    private val gitHubApi: GitHubApi,
    private val roomDb: DBStorage,
    private val roomRepoDB: DBRepoStorage
) : UserReposRepository {

    override fun getUserByLogin(login: String): Single<GitHubUser> {
        return roomDb.getGitHubUserDao().getUserByLogin(login)
    }

    override fun getUserRepos(login: String): Single<List<GitHubRepo>> {
        return roomRepoDB.getRepoDao().getReposByLogin("$login%")
            .flatMap { repos_flat_map ->
                if (repos_flat_map.isEmpty()) {
                    gitHubApi.fetchUserRepositories(login)
                        .map { repos_map ->
                            roomRepoDB.getRepoDao().saveRepos(repos_map)
                            repos_map
                        }
                } else {
                    Single.just(repos_flat_map)
                }
            }
    }
}