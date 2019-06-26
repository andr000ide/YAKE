package com.example.yake.Models
import com.squareup.moshi.Json


data class UrlExample(
    @Json(name = "content")
    val content: String? = "",
    @Json(name = "title")
    val title: String? = ""
)