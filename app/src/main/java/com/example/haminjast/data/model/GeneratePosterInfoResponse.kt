package com.example.haminjast.data.model


import com.google.gson.annotations.SerializedName

data class GeneratePosterInfoResponse(
    @SerializedName("colors")
    val colors: List<String>,
    @SerializedName("description")
    val description: String,
    @SerializedName("tags")
    val tags: List<String>,
    @SerializedName("titles")
    val titles: List<String>
)