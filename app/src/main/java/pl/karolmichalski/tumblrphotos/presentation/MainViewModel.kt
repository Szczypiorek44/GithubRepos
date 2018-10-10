package pl.karolmichalski.tumblrphotos.presentation

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import pl.karolmichalski.tumblrphotos.data.ApiInterface
import javax.inject.Inject

class MainViewModel(app: App) : ViewModel() {

	class Factory(private val application: Application) : ViewModelProvider.NewInstanceFactory() {
		override fun <T : ViewModel?> create(modelClass: Class<T>): T {
			@Suppress("UNCHECKED_CAST")
			return MainViewModel(application as App) as T
		}
	}

	init {
		app.appComponent.inject(this)
	}

	@Inject
	lateinit var apiInterface: ApiInterface

}