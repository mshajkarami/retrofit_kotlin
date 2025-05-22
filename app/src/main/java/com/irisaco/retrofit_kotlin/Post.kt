package com.irisaco.retrofit_kotlin

import kotlinx.serialization.SerialName

data class Post(
    @SerialName("userId")
    var userId : Int,
    @SerialName("id")
    var id : Int ,
    @SerialName("title")
    var title : String,
    @SerialName("body")
    var body : String
)
