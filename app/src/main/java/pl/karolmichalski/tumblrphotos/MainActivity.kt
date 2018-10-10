package pl.karolmichalski.tumblrphotos

import androidx.databinding.DataBindingUtil
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pl.karolmichalski.tumblrphotos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
			setLifecycleOwner(this@MainActivity)
		}
		setContentView(R.layout.activity_main)
	}
}
