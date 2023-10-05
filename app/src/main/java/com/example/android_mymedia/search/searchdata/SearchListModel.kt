package com.example.android_mymedia.search.searchdata

data class SearchListModel(
    val id: String,//고민이 필요함
    val channelTitle: String,
    val defaultImgUrl: String,
    val mediumImgUrl : String,
    val highImgUrl : String,
    val title: String,
    val description: String,
    val url: String
)


/*
fun SearchListModel.toSearchEntity():SearchEntity{
    return SearchEntity(

    )
}
*/
