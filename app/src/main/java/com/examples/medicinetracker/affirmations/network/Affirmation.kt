package com.examples.medicinetracker.affirmations.network

import com.squareup.moshi.Json

data class Affirmation (
    @Json(name = "text") val quote: String
)