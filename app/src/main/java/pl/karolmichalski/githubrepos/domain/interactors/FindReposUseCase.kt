package pl.karolmichalski.githubrepos.domain.interactors

import io.reactivex.Scheduler
import io.reactivex.Single
import pl.karolmichalski.githubrepos.data.models.Repo
import pl.karolmichalski.githubrepos.domain.interactors.base.SingleUseCase
import pl.karolmichalski.githubrepos.domain.repositories.GithubRepos
import javax.inject.Inject

class FindReposUseCase @Inject constructor(
		executionScheduler: Scheduler,
		postExecutionScheduler: Scheduler,
		private val githubRepos: GithubRepos)
	: SingleUseCase<List<Repo>, String>(executionScheduler, postExecutionScheduler) {

	override fun buildUseCaseSingle(params: String?): Single<List<Repo>> = githubRepos.findRepos(params)
}