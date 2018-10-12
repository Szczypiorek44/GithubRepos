package pl.karolmichalski.githubrepos.data

import io.reactivex.Single
import pl.karolmichalski.githubrepos.data.models.FindReposResponse
import pl.karolmichalski.githubrepos.data.models.Repo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

	@GET("/search/repositories")
	fun findRepos(@Query("q") keyword: String): Single<FindReposResponse>

	@GET("/repos/{owner}/{repo}")
	fun getRepoDetails(@Path("owner") owner: String, @Path("repo") repo: String): Single<Repo>

}