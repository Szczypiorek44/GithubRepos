package pl.karolmichalski.githubrepos.presentation.screens.repos

import android.app.Application
import androidx.lifecycle.*
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import pl.karolmichalski.githubrepos.ReposDataSource
import pl.karolmichalski.githubrepos.data.exceptions.BlankInputException
import pl.karolmichalski.githubrepos.data.models.Repo
import pl.karolmichalski.githubrepos.domain.GithubRepos
import pl.karolmichalski.githubrepos.presentation.App
import javax.inject.Inject

class ReposViewModel(app: App) : AndroidViewModel(app) {

	class Factory(private val application: Application) : ViewModelProvider.NewInstanceFactory() {
		override fun <T : ViewModel?> create(modelClass: Class<T>): T {
			@Suppress("UNCHECKED_CAST")
			return ReposViewModel(application as App) as T
		}
	}

	val keywords = MutableLiveData<String>()
	val repoList = MutableLiveData<List<Repo>>().apply { value = ArrayList() }
	val isLoading = MutableLiveData<Boolean>().apply { value = false }
	val errorMessage = MutableLiveData<String>()

	var repoList2: LiveData<PagedList<Repo>>

	private val compositeDisposable = CompositeDisposable()
	private val pageSize = 5
	private val reposDataSourceFactory: ReposDataSource.Factory

	@Inject
	lateinit var githubRepos: GithubRepos

	init {
		app.appComponent.inject(this)
		reposDataSourceFactory = ReposDataSource.Factory(githubRepos, compositeDisposable)
		val config = PagedList.Config.Builder()
				.setPageSize(pageSize)
				.setInitialLoadSizeHint(pageSize)
				.setEnablePlaceholders(false)
				.build()
		repoList2 = LivePagedListBuilder<Int, Repo>(reposDataSourceFactory, config).build()
	}

	override fun onCleared() {
		super.onCleared()
		compositeDisposable.dispose()
	}


	fun getState(): LiveData<Int> = Transformations.switchMap<ReposDataSource, Int>(reposDataSourceFactory.reposDataSourceLiveData, ReposDataSource::state)

	fun retry() {
		reposDataSourceFactory.reposDataSourceLiveData.value?.retry()
	}

	fun listIsEmpty(): Boolean {
		return repoList2.value?.isEmpty() ?: true
	}


	fun findRepos() {
		githubRepos.findRepos(keywords.value)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.doOnSubscribe { isLoading.postValue(true) }
				.doFinally { isLoading.postValue(false) }
				.subscribeBy(
						onSuccess = { repoList.value = it },
						onError = {
							if (it is BlankInputException)
								repoList.value = ArrayList()
							errorMessage.value = it.localizedMessage
						})

	}

}