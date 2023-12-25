package com.example.haminjast.data.model


import com.google.gson.annotations.SerializedName

data class ReadMessageResponse(
    @SerializedName("message")
    val message: String
)