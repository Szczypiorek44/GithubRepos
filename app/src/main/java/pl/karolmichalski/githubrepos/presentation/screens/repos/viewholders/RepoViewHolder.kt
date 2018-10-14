package pl.karolmichalski.githubrepos.presentation.screens.repos.viewholders

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import pl.karolmichalski.githubrepos.R
import pl.karolmichalski.githubrepos.data.models.Repo
import pl.karolmichalski.githubrepos.databinding.ItemRepoBinding

class RepoViewHolder(private val binding: ItemRepoBinding,
					 private val onItemClick: (Repo) -> Unit)
	: RecyclerView.ViewHolder(binding.root), View.OnClickListener {

	companion object {
		fun create(parent: ViewGroup, onItemClick: (Repo) -> Unit): RepoViewHolder {
			val binding = DataBindingUtil.inflate<ItemRepoBinding>(LayoutInflater.from(parent.context), R.layout.item_repo, parent, false)
			return RepoViewHolder(binding, onItemClick)
		}
	}

	init {
		binding.listener = this
	}

	override fun onClick(v: View?) {
		binding.repo?.apply {
			onItemClick(this)
		}
	}

	fun bind(repo: Repo?) {
		binding.repo = repo
	}
}