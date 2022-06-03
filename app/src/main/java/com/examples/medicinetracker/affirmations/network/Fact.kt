package com.examples.medicinetracker.affirmations.network

import com.squareup.moshi.Json

data class Fact (
    @Json(name = "fact") val fact: String
)

data class Contents (
    @Json(name = "contents") val contents: Fact
)