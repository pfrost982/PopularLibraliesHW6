package ru.geekbrains.mvpuser

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.App
import ru.geekbrains.R
import ru.geekbrains.data.GitHubUser
import ru.geekbrains.databinding.ViewUserBinding

class UserFragment: MvpAppCompatFragment(R.layout.view_user), UserView {

    private lateinit var viewBinding: ViewUserBinding

    private val userLogin: String by lazy {
        arguments?.getString(ARG_USER_LOGIN).orEmpty()
    }

    private val presenter: UserPresenter by moxyPresenter {
        UserPresenter(userLogin = userLogin).apply {
            App.instance.component.provideUserComponent().build().inject(this)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = ViewUserBinding.bind(view)
        viewBinding.userLogin.text = userLogin
    }

    override fun showUser(user: GitHubUser) {
        Glide.with(viewBinding.userAvatar.context)
            .load(user.avatarUrl)
            .into(viewBinding.userAvatar)
        viewBinding.userLogin.text = user.login
        viewBinding.url.text = user.url
    }

    override fun showReposList(reposList: String) {
        viewBinding.repos.text = reposList
    }

    override fun showError(message: String) {
        //Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        Snackbar.make(viewBinding.root, message, Snackbar.LENGTH_INDEFINITE)
            .setAction("Reload...") {
                presenter.updateContent()
            }.show()
    }

    companion object {
        private const val ARG_USER_LOGIN = "arg_user_login"

        fun newInstance(userId: String): Fragment =
            UserFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_USER_LOGIN, userId)
                }
            }
    }
}