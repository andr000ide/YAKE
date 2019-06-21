package com.example.yake.Models

import com.squareup.moshi.Json

data class Wordcloud(
    @Json(name="wordcloudb64") val wordcloudb64: String
)