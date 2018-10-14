package pl.karolmichalski.githubrepos.domain

import dagger.Component
import pl.karolmichalski.githubrepos.data.repos.ReposModule
import pl.karolmichalski.githubrepos.presentation.screens.details.DetailsViewModel
import pl.karolmichalski.githubrepos.presentation.screens.repos.ReposViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [ReposModule::class])
interface AppComponent {
	fun inject(reposViewModel: ReposViewModel)
	fun inject(detailsViewModel: DetailsViewModel)
}