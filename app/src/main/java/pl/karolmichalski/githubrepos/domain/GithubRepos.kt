package pl.karolmichalski.githubrepos.domain

import io.reactivex.Single
import pl.karolmichalski.githubrepos.data.models.Repo

interface GithubRepos {
	fun findRepos(keywords: String?): Single<List<Repo>>
	fun findRepos(keywords: String?,page: Int): Single<List<Repo>>
	fun getRepoDetails(owner: String, repo: String): Single<Repo>
}