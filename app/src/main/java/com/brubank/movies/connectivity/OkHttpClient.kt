package com.brubank.movies.connectivity

import com.brubank.movies.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException
import kotlin.jvm.Throws

class OkHttpClient {
    companion object {

        fun makeClient(): OkHttpClient =
            OkHttpClient.Builder()
                .addInterceptor(makeInterceptor())
                .build()

        private fun makeInterceptor(): Interceptor =
            object: Interceptor {
                @Throws(IOException::class)
                override fun intercept(chain: Interceptor.Chain): Response {
                    var request = chain.request()
                    request = request.newBuilder().apply {
                        url(addQueryParamsToURL(request.url.newBuilder()))
                    }.build()
                    return chain.proceed(request)
                }
            }

        private fun addQueryParamsToURL(urlBuilder: HttpUrl.Builder) = urlBuilder.apply {
            addQueryParameter("api_key", BuildConfig.API_KEY)
        }.build()

    }
}