package com.example.haminjast


import com.google.gson.annotations.SerializedName

data class ReadMessageResponse(
    @SerializedName("message")
    val message: String
)