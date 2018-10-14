package pl.karolmichalski.githubrepos

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import pl.karolmichalski.githubrepos.data.models.Repo
import pl.karolmichalski.githubrepos.domain.GithubRepos

const val DONE = 0
const val LOADING = 1
const val ERROR = 2

class ReposDataSource(private val githubRepos: GithubRepos,
					  private val compositeDisposable: CompositeDisposable)
	: PageKeyedDataSource<Int, Repo>() {

	var state = MutableLiveData<Int>()
	private var retryCompletable: Completable? = null

	override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Repo>) {
		updateState(LOADING)
		compositeDisposable.add(githubRepos.findRepos("a").subscribeBy(
				onSuccess = {
					updateState(DONE)
					callback.onResult(it, null, 2)
				},
				onError = {
					updateState(ERROR)
					setRetry(Action { loadInitial(params, callback) })
				}
		)
		)

	}

	override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Repo>) {
		updateState(LOADING)
		compositeDisposable.add(githubRepos.findRepos("a", params.key).subscribeBy(
				onSuccess = {
					updateState(DONE)
					callback.onResult(it, params.key + 1)
				},
				onError = {
					updateState(ERROR)
					setRetry(Action { loadAfter(params, callback) })
				}

		))
	}

	override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Repo>) {
	}

	private fun updateState(state: Int) {
		this.state.postValue(state)
	}

	fun retry() {
		if (retryCompletable != null) {
			compositeDisposable.add(retryCompletable!!
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe())
		}
	}

	private fun setRetry(action: Action?) {
		retryCompletable = if (action == null) null else Completable.fromAction(action)
	}

	class Factory(
			private val githubRepos: GithubRepos,
			private val compositeDisposable: CompositeDisposable)
		: DataSource.Factory<Int, Repo>() {

		val reposDataSourceLiveData = MutableLiveData<ReposDataSource>()

		override fun create(): DataSource<Int, Repo> {
			val reposDataSource = ReposDataSource(githubRepos, compositeDisposable)
			reposDataSourceLiveData.postValue(reposDataSource)
			return reposDataSource
		}


	}

}