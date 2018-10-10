package pl.karolmichalski.tumblrphotos.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import pl.karolmichalski.tumblrphotos.R
import pl.karolmichalski.tumblrphotos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

	private val viewModel by lazy {
		ViewModelProviders.of(this, MainViewModel.Factory(application)).get(MainViewModel::class.java)
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
			setLifecycleOwner(this@MainActivity)
			viewModel = this@MainActivity.viewModel
		}
	}
}
