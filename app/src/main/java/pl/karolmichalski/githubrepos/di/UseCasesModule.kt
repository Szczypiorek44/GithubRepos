package pl.karolmichalski.githubrepos.di

import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import pl.karolmichalski.githubrepos.domain.interactors.FindReposUseCase
import pl.karolmichalski.githubrepos.domain.repositories.GithubRepos
import javax.inject.Named

@Module
class UseCasesModule {

	@Provides
	fun provideFindListUseCase(
			@Named(SCHEDULER_IO) executionScheduler: Scheduler,
			@Named(SCHEDULER_MAIN_THREAD) postExecutionScheduler: Scheduler,
			githubRepos: GithubRepos)
			: FindReposUseCase = FindReposUseCase(executionScheduler, postExecutionScheduler, githubRepos)
}