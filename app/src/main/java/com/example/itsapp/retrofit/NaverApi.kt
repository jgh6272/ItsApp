package com.example.itsapp.retrofit

import com.example.itsapp.model.vo.Blog
import com.example.itsapp.model.vo.News
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NaverApi {
    /*suspend fun : 오랫동안 비동기로 사용될 코드라는 뜻*/
    @GET("search/news.json")
    suspend fun getSearchNews(
        @Query("query") query: String,
        @Query("start") start:Int? = null,
        @Query("display") display:Int? = null
    ): News

    @GET("search/blog.json")
    suspend fun getSearchBlog(
        @Query("query") query: String,
        @Query("start") start:Int? = null,
        @Query("display") display:Int? = null
    ): Blog

    companion object{
        const val BASE_URL = "https://openapi.naver.com/v1/"
        const val CLIENT_ID = "EYOvSd046ngfoYNGtu7q"
        const val CLIENT_SECRET = "bY3QkVIHo_"

        fun create():NaverApi{
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val headerInterceptor = Interceptor {
                val request = it.request()
                    .newBuilder()
                    .addHeader("X-Naver-Client-Id", CLIENT_ID)
                    .addHeader("X-Naver-Client-Secret", CLIENT_SECRET)
                    .build()
                return@Interceptor it.proceed(request)
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(headerInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NaverApi::class.java)
        }
    }
}