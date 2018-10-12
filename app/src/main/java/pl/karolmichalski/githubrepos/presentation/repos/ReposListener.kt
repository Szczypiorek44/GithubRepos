package pl.karolmichalski.githubrepos.presentation.repos

import pl.karolmichalski.githubrepos.data.models.Repo

interface ReposListener {
	fun onSearchClick()

	fun onItemClick(): (Repo) -> Unit
}