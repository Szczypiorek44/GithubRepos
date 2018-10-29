package pl.karolmichalski.githubrepos.presentation.screens.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_details.*
import pl.karolmichalski.githubrepos.R
import pl.karolmichalski.githubrepos.databinding.ActivityDetailsBinding
import pl.karolmichalski.githubrepos.presentation.dialogs.DecisionDialog
import pl.karolmichalski.githubrepos.presentation.utils.IntentDelegate

class DetailsActivity : AppCompatActivity() {

	companion object {
		private var Intent.owner by IntentDelegate.String("owner")
		private var Intent.repo by IntentDelegate.String("repo")

		fun getIntent(context: Context, owner: String, repoName: String): Intent {
			return Intent(context, DetailsActivity::class.java).also {
				it.owner = owner
				it.repo = repoName
			}
		}
	}

	private val viewModel by lazy {
		ViewModelProviders.of(this, DetailsViewModel.Factory(application)).get(DetailsViewModel::class.java)
	}

	private val binding by lazy {
		DataBindingUtil.setContentView<ActivityDetailsBinding>(this, R.layout.activity_details)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding.let {
			it.setLifecycleOwner(this)
			it.viewModel = viewModel
		}
		viewModel.repo.observe(this, Observer { binding.invalidateAll() })
		viewModel.errorMessage.observe(this, Observer { Toast.makeText(this, it, Toast.LENGTH_SHORT).show() })
		viewModel.getRepoDetails(intent.owner, intent.repo)
		card2.setOnClickListener { showDecisionDialog() }
	}

	private fun showDecisionDialog() {
		DecisionDialog().apply {
			title = this@DetailsActivity.getString(R.string.are_you_ready_for_some_magic_question)
			button1text = this@DetailsActivity.getString(R.string.yes)
			button2text = this@DetailsActivity.getString(R.string.no)
			onButton1Click = {
				dismiss()
				val magic = viewModel.getMagic(viewModel.repo.value)
				Toast.makeText(activity, this@DetailsActivity.getString(R.string.magic_number_is, magic), Toast.LENGTH_SHORT).show()
			}
			onButton2Click = {
				dismiss()
			}
			show(supportFragmentManager, DecisionDialog::class.java.simpleName)
		}
	}
}