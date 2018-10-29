package pl.karolmichalski.githubrepos.presentation.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import pl.karolmichalski.githubrepos.R
import pl.karolmichalski.githubrepos.databinding.DialogDecisionBinding

class DecisionDialog : DialogFragment() {

	companion object {
		fun newInstance(title: String, button1text: String, button2text: String): DecisionDialog {
			return DecisionDialog().apply {
				this.title = title
				this.button1text = button1text
				this.button2text = button2text
			}
		}
	}

	private var title: String? = null
	private var button1text = ""
	private var button2text = ""

	var onButton1Click: () -> Unit = {}
	var onButton2Click: () -> Unit = {}

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
		return DataBindingUtil.inflate<DialogDecisionBinding>(inflater, R.layout.dialog_decision, container, false).also {
			it.setLifecycleOwner(this)
			it.title = this.title
			it.button1text = this.button1text
			it.button2text = this.button2text
			it.onButton1Click = this.onButton1Click
			it.onButton2Click = this.onButton2Click
		}.root
	}
}