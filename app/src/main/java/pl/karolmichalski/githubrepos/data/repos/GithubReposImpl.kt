package pl.karolmichalski.githubrepos.data.repos

import io.reactivex.Single
import pl.karolmichalski.githubrepos.data.models.Repo
import pl.karolmichalski.githubrepos.domain.GithubRepos

class GithubReposImpl(private val apiInterface: ApiInterface) : GithubRepos {

	override fun findRepos(keywords: String?): Single<List<Repo>> {
		return when {
			keywords.isNullOrBlank() -> Single.fromCallable { throw Exception("Enter keywords") }
			else -> apiInterface.findRepos(keywords!!).flatMap { Single.just(it.repoList) }
		}
	}

	override fun getRepoDetails(owner: String, repo: String): Single<Repo> {
		return apiInterface.getRepoDetails(owner, repo)
	}

}