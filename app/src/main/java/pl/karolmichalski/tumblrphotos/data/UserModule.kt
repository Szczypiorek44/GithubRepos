package pl.karolmichalski.tumblrphotos.data

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import javax.inject.Singleton

private const val API_URL = "https://www.tumblr.com/docs/en/api/v1/"

@Module
class UserModule {

	@Provides
	@Singleton
	fun provideApiInterface(): ApiInterface {

		val okHttpClient = OkHttpClient.Builder()
				.addInterceptor(HttpLoggingInterceptor())
				.build()

		val objectMapper = ObjectMapper()
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

		val retrofit = Retrofit.Builder()
				.baseUrl(API_URL)
				.addCallAdapterFactory(RxJava2CallAdapterFactory.create())
				.client(okHttpClient)
				.addConverterFactory(JacksonConverterFactory.create(objectMapper))
				.build()

		return retrofit.create(ApiInterface::class.java)
	}
}