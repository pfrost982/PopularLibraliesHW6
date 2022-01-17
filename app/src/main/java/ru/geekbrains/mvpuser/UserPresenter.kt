package ru.geekbrains.mvpuser

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import moxy.MvpPresenter
import ru.geekbrains.data.GitHubUserRepository
import ru.geekbrains.mvpuser.user_room.DBRepoStorage
import javax.inject.Inject

class UserPresenter(private val userLogin: String) : MvpPresenter<UserView>() {

    @Inject
    lateinit var userReposRepository: UserReposRepository

    override fun onFirstViewAttach() {
        updateContent()
    }

    fun updateContent() {
        userReposRepository
            .getUserByLogin(userLogin)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ user ->
                viewState.showUser(user)
            }, { error ->
                viewState.showError(error.message.toString())
            })

        userReposRepository
            .getUserRepos(userLogin)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ reposList ->
                var reposString = ""
                reposList.forEach { repo ->
                    reposString = reposString + repo.full_name + "\n"
                }
                viewState.showReposList(reposString)
            }, { error ->
                viewState.showError(error.message.toString())
            })
    }
}

