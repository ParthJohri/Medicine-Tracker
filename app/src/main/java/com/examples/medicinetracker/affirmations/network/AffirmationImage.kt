package com.examples.medicinetracker.affirmations.network

import com.squareup.moshi.Json

data class AffirmationImage(
    val id: String,
    @Json(name = "download_url") val imgSrcUrl: String
)
