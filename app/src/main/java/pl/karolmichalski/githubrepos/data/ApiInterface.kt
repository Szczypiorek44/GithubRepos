package pl.karolmichalski.githubrepos.data

import io.reactivex.Single
import pl.karolmichalski.githubrepos.data.models.FindReposResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

	@GET("/search/repositories")
	fun findRepos(@Query("q") keyword: String): Single<FindReposResponse>

}