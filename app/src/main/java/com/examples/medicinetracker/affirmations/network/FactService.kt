package com.examples.medicinetracker.affirmations.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

private const val BASE_URL_QUOTE = "https://api.fungenerators.com/fact/"
private const val BASE_URL_IMG = "https://android-kotlin-fun-mars-server.appspot.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL_QUOTE)
    .build()

interface FactsApiService {

    @Headers("X-Fungenerators-Api-Secret: hdJVsyM8toqRMddWk3vjSweF")
    @GET("random?category=health")
    suspend fun getContents(): Contents
}

object FactsApi {
    val retrofitService: FactsApiService by lazy { retrofit.create(FactsApiService::class.java)}
}