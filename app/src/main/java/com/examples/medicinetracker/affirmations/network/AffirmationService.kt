package com.examples.medicinetracker.affirmations.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL_QUOTE = "https://type.fit/api/"
private const val BASE_URL_IMG = "https://android-kotlin-fun-mars-server.appspot.com/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL_QUOTE)
    .build()

interface AffirmationApiService {

    @GET("quotes")
    suspend fun getQuotes(): List<Affirmation>
}

object AffirmationApi {
    val retrofitService: AffirmationApiService by lazy { retrofit.create(AffirmationApiService::class.java)}
}