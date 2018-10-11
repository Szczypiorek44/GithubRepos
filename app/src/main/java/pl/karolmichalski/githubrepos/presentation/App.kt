package pl.karolmichalski.githubrepos.presentation

import android.app.Application
import pl.karolmichalski.githubrepos.domain.AppComponent
import pl.karolmichalski.githubrepos.domain.DaggerAppComponent

class App : Application() {

	val appComponent: AppComponent by lazy {
		DaggerAppComponent.create()
	}
}