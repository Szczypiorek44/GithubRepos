package pl.karolmichalski.githubrepos.presentation.screens.repos

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import pl.karolmichalski.githubrepos.R
import pl.karolmichalski.githubrepos.data.models.Repo
import pl.karolmichalski.githubrepos.databinding.ActivityReposBinding
import pl.karolmichalski.githubrepos.presentation.screens.details.DetailsActivity
import pl.karolmichalski.githubrepos.presentation.screens.details.owner
import pl.karolmichalski.githubrepos.presentation.screens.details.repo

class ReposActivity : AppCompatActivity(), ReposListener {

	private val viewModel by lazy {
		ViewModelProviders.of(this, ReposViewModel.Factory(application)).get(ReposViewModel::class.java)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		DataBindingUtil.setContentView<ActivityReposBinding>(this, R.layout.activity_repos).apply {
			setLifecycleOwner(this@ReposActivity)
			viewModel = this@ReposActivity.viewModel
			listener = this@ReposActivity
		}
		viewModel.errorMessage.observe(this, Observer {
			Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
		})
	}

	override fun onSearchClick() {
		viewModel.findRepos()
	}

	override fun onItemClick(): (Repo) -> Unit {
		return {
			val intent = Intent(this, DetailsActivity::class.java).apply {
				owner = it.owner.login
				repo = it.name
			}
			startActivity(intent)
		}
	}

}
