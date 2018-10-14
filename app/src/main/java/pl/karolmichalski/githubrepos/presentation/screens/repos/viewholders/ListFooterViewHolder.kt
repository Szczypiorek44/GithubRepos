package pl.karolmichalski.githubrepos.presentation.screens.repos.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_list_footer.view.*
import pl.karolmichalski.githubrepos.ERROR
import pl.karolmichalski.githubrepos.LOADING
import pl.karolmichalski.githubrepos.R

class ListFooterViewHolder(view: View) : RecyclerView.ViewHolder(view) {

	fun bind(status: Int?) {
		itemView.progress_bar.visibility = if (status == LOADING) View.VISIBLE else View.INVISIBLE
		itemView.txt_error.visibility = if (status == ERROR) View.VISIBLE else View.INVISIBLE
	}

	companion object {
		fun create(retry: () -> Unit, parent: ViewGroup): ListFooterViewHolder {
			val view = LayoutInflater.from(parent.context)
					.inflate(R.layout.item_list_footer, parent, false)
			view.txt_error.setOnClickListener { retry() }
			return ListFooterViewHolder(view)
		}
	}
}