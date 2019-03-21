package ru.jjba.jr2.presentation.util

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NavDetail(
        val title: String,
        val fragmentId: Long,
        val fragmentType: String
)