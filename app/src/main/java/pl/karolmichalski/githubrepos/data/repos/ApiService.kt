package pl.karolmichalski.githubrepos.data.repos

import io.reactivex.Single
import pl.karolmichalski.githubrepos.data.models.FindReposResponse
import pl.karolmichalski.githubrepos.data.models.Repo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

	@GET("/search/repositories")
	fun findRepos(@Query("q") keywords: String): Single<FindReposResponse>

	@GET("/search/repositories")
	fun findRepos(@Query("q") keywords: String, @Query("page") page: Int): Single<FindReposResponse>

	@GET("/repos/{owner}/{repo}")
	fun getRepoDetails(@Path("owner") owner: String, @Path("repo") repo: String): Single<Repo>

}