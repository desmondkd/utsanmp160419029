package com.example.s160419029.model

import com.google.gson.annotations.SerializedName

data class Book(
    val id:Int?,
    var title:String?,
    var date:String?,
    var author:String?,
    var publisher:String?,
    @SerializedName("photo_url")
    var photoUrl:String?
)
data class Publisher(
    val id:Int?,
    var name:String?,
    var desc:String?,
    @SerializedName("photo_url")
    var photoUrl:String?
)
data class Author(
    val id:Int?,
    var name:String?,
    var desc:String?,
    @SerializedName("photo_url")
    var photoUrl:String?
)
data class Profile(
    val username:String?,
    var password:String?,
    @SerializedName("photo_url")
    var photoUrl:String?
)