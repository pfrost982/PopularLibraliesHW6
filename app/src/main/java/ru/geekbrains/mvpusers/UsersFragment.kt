package ru.geekbrains.mvpusers

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import ru.geekbrains.App
import ru.geekbrains.R
import ru.geekbrains.data.GitHubUser
import ru.geekbrains.databinding.ViewUsersBinding
import ru.geekbrains.recycler.UsersAdapter

class UsersFragment : MvpAppCompatFragment(R.layout.view_users), UsersView,
    UsersAdapter.OnUserClickListener {

    private val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter().apply {
            App.instance.component.inject(this)
        }
    }

    private val usersAdapter = UsersAdapter(this)
    private lateinit var viewBinging: ViewUsersBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinging = ViewUsersBinding.bind(view)
        viewBinging.usersRecycler.adapter = usersAdapter
    }

    override fun showUsers(users: List<GitHubUser>) {
        usersAdapter.submitList(users)
    }

    override fun onUserPicked(user: GitHubUser) {
        presenter.goToNextScreen(user.login!!)
    }

    override fun showError(message: String) {
        //Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        Snackbar.make(viewBinging.root, message, Snackbar.LENGTH_INDEFINITE)
            .setAction("Reload...") {
                presenter.updateContent()
            }.show()
    }

    override fun setProgressBarVisibility(isVisible: Boolean){
        if (isVisible) viewBinging.progress.visibility = View.VISIBLE
        else viewBinging.progress.visibility = View.GONE
    }

    companion object {
        fun newInstance(): Fragment = UsersFragment()
    }
}