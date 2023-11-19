package com.yudha.myapplication.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ConfigNetwork {
    companion object{
        private const val BASE_URL = "https://pokeapi.co/api/v2/"

        fun provideOkHttpClient() : OkHttpClient {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val builder = OkHttpClient.Builder().addInterceptor(interceptor).apply {
                readTimeout(20, TimeUnit.SECONDS)
                connectTimeout(10, TimeUnit.SECONDS)
            }
            return builder.build()
        }

        private val gson = GsonBuilder()
            .setLenient()
            .create()

        fun getNetwork() : ServiceApi{
            val retrofit = Retrofit.Builder()
                .client(provideOkHttpClient())
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build()

            return retrofit.create(ServiceApi::class.java)
        }

    }
}