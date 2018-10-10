package pl.karolmichalski.tumblrphotos.presentation

import android.app.Application
import pl.karolmichalski.tumblrphotos.domain.AppComponent
import pl.karolmichalski.tumblrphotos.domain.DaggerAppComponent

class App : Application() {

	val appComponent: AppComponent by lazy {
		DaggerAppComponent.create()
	}
}