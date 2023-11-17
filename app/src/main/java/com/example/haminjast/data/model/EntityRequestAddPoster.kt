package com.example.haminjast.data.model

data class RequestBodyForAddPoster(
    val addresses: List<AddressAddPoster>,
    val img_urls: List<String>,
    val poster: AddPoster,
    val tags: List<String>
)

data class AddressAddPoster(
    val address_detail: String,
    val city: String,
    val latitude: Double,
    val longitude: Double,
    val province: String
)

data class AddPoster(
    val alert: Boolean,
    val award: Int,
    val chat: Boolean,
    val description: String,
    val special_type: String,
    val state: String,
    val status: String,
    val tel_id: String?,
    val title: String,
    val user_phone: String?
)