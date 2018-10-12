package pl.karolmichalski.githubrepos.domain

import dagger.Component
import pl.karolmichalski.githubrepos.data.RepositoryModule
import pl.karolmichalski.githubrepos.presentation.repos.ReposViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class])
interface AppComponent {
	fun inject(reposViewModel: ReposViewModel)
}