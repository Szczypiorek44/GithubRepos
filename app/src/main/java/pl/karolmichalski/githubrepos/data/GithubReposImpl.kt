package pl.karolmichalski.githubrepos.data

import io.reactivex.Single
import pl.karolmichalski.githubrepos.data.models.Repo
import pl.karolmichalski.githubrepos.domain.GithubRepos

class GithubReposImpl(private val apiInterface: ApiInterface) : GithubRepos {

	override fun findRepos(keyword: String): Single<List<Repo>> {
		return when {
			keyword.isBlank() -> Single.fromCallable { throw Exception("Enter keywords") }
			else -> apiInterface.findRepos(keyword).flatMap { Single.just(it.repoList) }
		}
	}

	override fun getRepoDetails(owner: String, repo: String): Single<Repo> {
		return apiInterface.getRepoDetails(owner, repo)
	}

}