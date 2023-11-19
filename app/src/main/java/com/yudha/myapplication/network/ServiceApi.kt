package com.yudha.myapplication.network

import com.yudha.myapplication.activity.model.DetaiResult
import com.yudha.myapplication.activity.model.HomePageResult
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface ServiceApi {
    @GET("pokemon")
    fun loadHomePokemon() : Flowable<HomePageResult>

    @GET("pokemon/{name}")
    fun detailPokemon(
        @Path("name") name: String
    ): Flowable<DetaiResult>
}