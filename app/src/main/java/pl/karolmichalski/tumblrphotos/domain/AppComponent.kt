package pl.karolmichalski.tumblrphotos.domain

import dagger.Component
import pl.karolmichalski.tumblrphotos.data.UserModule
import pl.karolmichalski.tumblrphotos.presentation.MainViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [UserModule::class])
interface AppComponent {
	fun inject(mainViewModel: MainViewModel)
}