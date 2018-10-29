package pl.karolmichalski.githubrepos.di

import dagger.Module
import dagger.Provides
import pl.karolmichalski.githubrepos.domain.interactors.FindReposUseCase
import pl.karolmichalski.githubrepos.domain.repositories.GithubRepos

@Module
class UseCasesModule {

	@Provides
	fun provideFindReposUseCase(githubRepos: GithubRepos)
			: FindReposUseCase = FindReposUseCase(githubRepos)


}