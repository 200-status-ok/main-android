package com.example.haminjast.data.model


import com.google.gson.annotations.SerializedName

data class VOR(
    @SerializedName("id")
    val id: Int,
    @SerializedName("message")
    val message: String,
    @SerializedName("token")
    val token: String
)