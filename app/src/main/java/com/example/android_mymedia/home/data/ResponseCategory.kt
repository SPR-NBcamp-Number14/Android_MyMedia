package com.example.android_mymedia.home.data

import com.google.gson.annotations.SerializedName

data class ResponseCategory(
    @SerializedName("item")
    val items: List<CategoryItems>
)

data class CategoryItems(
    @SerializedName("id")
    val id: String,
    @SerializedName("snippet")
    val snippet: CategorySnippet
)

data class CategorySnippet(
    val title: String? = "지원 하지 않음",
    val assignable: Boolean? = false
)
