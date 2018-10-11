package pl.karolmichalski.githubrepos.data

import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

	@GET("/search/repositories")
	fun findRepository(@Query("q") keyword: String): Single<ResponseBody>

}