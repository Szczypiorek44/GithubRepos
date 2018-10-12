package pl.karolmichalski.githubrepos.presentation.repos

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import pl.karolmichalski.githubrepos.R
import pl.karolmichalski.githubrepos.databinding.ActivityReposBinding

class ReposActivity : AppCompatActivity() {

	//collapsable action bar

	private val viewModel by lazy {
		ViewModelProviders.of(this, ReposViewModel.Factory(application)).get(ReposViewModel::class.java)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		DataBindingUtil.setContentView<ActivityReposBinding>(this, R.layout.activity_repos).apply {
			setLifecycleOwner(this@ReposActivity)
			viewModel = this@ReposActivity.viewModel
		}
		viewModel.findRepository()
	}
}
