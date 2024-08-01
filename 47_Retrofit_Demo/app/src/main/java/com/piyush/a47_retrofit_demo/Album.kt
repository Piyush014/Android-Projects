package com.piyush.a47_retrofit_demo

import com.google.gson.annotations.SerializedName

data class Album(
    @SerializedName("id")
    val id: Int,

    @SerializedName("userId")
    val userId: Int,

    @SerializedName("title")
    val title: String
)
