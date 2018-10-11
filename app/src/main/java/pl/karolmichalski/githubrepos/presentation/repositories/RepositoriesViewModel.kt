package pl.karolmichalski.githubrepos.presentation.repositories

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import pl.karolmichalski.githubrepos.data.ApiInterface
import pl.karolmichalski.githubrepos.presentation.App
import javax.inject.Inject

class RepositoriesViewModel(app: App) : ViewModel() {

	class Factory(private val application: Application) : ViewModelProvider.NewInstanceFactory() {
		override fun <T : ViewModel?> create(modelClass: Class<T>): T {
			@Suppress("UNCHECKED_CAST")
			return RepositoriesViewModel(application as App) as T
		}
	}

	init {
		app.appComponent.inject(this)
	}

	@Inject
	lateinit var apiInterface: ApiInterface

	fun findRepository() {
		apiInterface.findRepository("retrofit")
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeBy(
						onSuccess = {
							Log.d("awdwa", "awdawdaw")
						},
						onError = {
							Log.d("awdwa", "awdawdaw")
						})
	}

}