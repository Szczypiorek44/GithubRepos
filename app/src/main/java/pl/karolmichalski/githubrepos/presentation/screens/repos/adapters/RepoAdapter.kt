package pl.karolmichalski.githubrepos.presentation.screens.repos.adapters

import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import pl.karolmichalski.githubrepos.ERROR
import pl.karolmichalski.githubrepos.LOADING
import pl.karolmichalski.githubrepos.data.models.Repo
import pl.karolmichalski.githubrepos.presentation.screens.repos.viewholders.ListFooterViewHolder
import pl.karolmichalski.githubrepos.presentation.screens.repos.viewholders.RepoViewHolder

@BindingAdapter("repoList", "onItemClick", "retry")
fun RecyclerView.setRepos(repoList: PagedList<Repo>?, onItemClick: (Repo) -> Unit, retry: () -> Unit) {
	if (adapter == null)
		adapter = RepoAdapter(onItemClick, retry)
	(adapter as RepoAdapter).submitList(repoList)
}

class RepoAdapter(private val onItemClick: (Repo) -> Unit,
				  private val retry: () -> Unit) : PagedListAdapter<Repo, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

	private val DATA_VIEW_TYPE = 1
	private val FOOTER_VIEW_TYPE = 2

	private var state = LOADING

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		return if (viewType == DATA_VIEW_TYPE) RepoViewHolder.create(parent, onItemClick) else ListFooterViewHolder.create(retry, parent)
	}

	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		if (getItemViewType(position) == DATA_VIEW_TYPE)
			(holder as RepoViewHolder).bind(getItem(position))
		else (holder as ListFooterViewHolder).bind(state)
	}

	override fun getItemViewType(position: Int): Int {
		return if (position < super.getItemCount()) DATA_VIEW_TYPE else FOOTER_VIEW_TYPE
	}

	companion object {
		private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Repo>() {
			override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
				return oldItem.id == newItem.id
			}

			override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
				return oldItem.name == newItem.name && oldItem.fullName == newItem.fullName
			}
		}
	}

	override fun getItemCount(): Int {
		return super.getItemCount() + if (hasFooter()) 1 else 0
	}

	private fun hasFooter(): Boolean {
		return super.getItemCount() != 0 && (state == LOADING || state == ERROR)
	}

	fun setState(state: Int) {
		this.state = state
		notifyItemChanged(super.getItemCount())
	}
}