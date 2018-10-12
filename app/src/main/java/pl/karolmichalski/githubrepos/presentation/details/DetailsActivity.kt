package pl.karolmichalski.githubrepos.presentation.details

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import pl.karolmichalski.githubrepos.R
import pl.karolmichalski.githubrepos.databinding.ActivityDetailsBinding
import pl.karolmichalski.githubrepos.presentation.utils.IntentDelegate

var Intent.owner by IntentDelegate.String("owner")
var Intent.repo by IntentDelegate.String("repo")

class DetailsActivity : AppCompatActivity(), DetailsListener {

	private val viewModel by lazy {
		ViewModelProviders.of(this, DetailsViewModel.Factory(application)).get(DetailsViewModel::class.java)
	}

	private val binding by lazy {
		DataBindingUtil.setContentView<ActivityDetailsBinding>(this, R.layout.activity_details)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding.apply {
			setLifecycleOwner(this@DetailsActivity)
			viewModel = this@DetailsActivity.viewModel
			listener = this@DetailsActivity
		}
		viewModel.repo.observe(this, Observer {
			binding.invalidateAll()
		})
		viewModel.errorMessage.observe(this, Observer {
			Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
		})
		viewModel.getRepoDetails(intent.owner, intent.repo)
	}
}