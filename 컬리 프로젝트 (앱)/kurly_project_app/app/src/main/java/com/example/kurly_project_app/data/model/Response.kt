package com.example.kurly_project_app.data.model


import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Response(
    val documents: List<Product?>?,
)