package com.brubank.movies.connectivity

import com.brubank.movies.BuildConfig.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.Date

class Retrofit {
    companion object {
        private val instance: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(
                    MoshiConverterFactory.create(
                        Moshi.Builder()
                            .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())

                            .build()
                    )
                )
                .client(OkHttpClient.makeClient())
                .build()
        }

        fun <T> getServiceRetrofit(service: Class<T>): T = instance.create(service)
    }
}