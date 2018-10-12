package pl.karolmichalski.githubrepos.presentation.repos

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import pl.karolmichalski.githubrepos.R
import pl.karolmichalski.githubrepos.data.models.Repo
import pl.karolmichalski.githubrepos.databinding.ActivityReposBinding

class ReposActivity : AppCompatActivity(), ReposListener {

	//collapsable action bar

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
			Toast.makeText(this, it.name, Toast.LENGTH_SHORT).show()
		}
	}

}
