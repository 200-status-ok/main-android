package com.example.haminjast.ui.screen.ads

data class PostersScreenState(
    val posterFilterOptions: PosterFilterOptions = PosterFilterOptions(),
    val isFilterOptionsExpanded: Boolean = false,
    val searchPhrase: String = ""
)