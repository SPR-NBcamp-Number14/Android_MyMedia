package com.example.android_mymedia.searchdata


data class ResponseSearch(
    val items: List<SearchItem>
)

data class SearchItem(
    val kind: String,
    val id: Searched,
    val snippet: SearchSnippet,
)
data class Searched(
    val searchId:String,
)

data class SearchSnippet(
    val publishedAt : String,
    val channelId : String,
    val title : String,
    val description : String,
    val thumbnails : SearchThumbnail
)
data class SearchThumbnail(
    val default : SearchThumbnails,
    val medium : SearchThumbnails,
    val high : SearchThumbnails
)

data class SearchThumbnails(
    val url : String
)


