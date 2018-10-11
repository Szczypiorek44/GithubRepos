package pl.karolmichalski.githubrepos.presentation.repositories

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import pl.karolmichalski.githubrepos.R
import pl.karolmichalski.githubrepos.databinding.ActivityRepositoriesBinding

class RepositoriesActivity : AppCompatActivity() {

	//collapsable action bar

	private val viewModel by lazy {
		ViewModelProviders.of(this, RepositoriesViewModel.Factory(application)).get(RepositoriesViewModel::class.java)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		DataBindingUtil.setContentView<ActivityRepositoriesBinding>(this, R.layout.activity_repositories).apply {
			setLifecycleOwner(this@RepositoriesActivity)
			viewModel = this@RepositoriesActivity.viewModel
		}
		viewModel.findRepository()
	}
}
