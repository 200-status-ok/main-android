package com.example.haminjast.data.model

import org.junit.Assert.assertEquals
import org.junit.Test

class GetPosterByIdResponseTest {

    @Test
    fun `should create valid get poster by id response`() {
        val addresses = listOf(
            GetPosterByIdResponse.Addresse(
                addressDetail = "Detail",
                city = "City",
                latitude = 123,
                longitude = 456,
                province = "Province",
                addressId = 1,
                createdAt = 123,
                deletedAt = GetPosterByIdResponse.Addresse.DeletedAt("time", true),
                id = 456,
                updatedAt = "updatedAt"
            )
        )
        val award = 100
        val createdAt = 1234567890
        val description = "This is a description"
        val id = 1
        val images = listOf(
            GetPosterByIdResponse.Image(
                createdAt = 1234567890,
                deletedAt = GetPosterByIdResponse.Image.DeletedAt("time", true),
                id = 1,
                posterId = 1,
                updatedAt = "2023-10-04T15:23:23Z",
                url = "https://example.com/image.jpg"
            )
        )
        val specialType = "Lost"
        val state = "Active"
        val status = "lost"
        val tags = listOf(GetPosterByIdResponse.Tag(
            createdAt = 1234567890,
            deletedAt = GetPosterByIdResponse.Tag.DeletedAt("time", true),
            id = 1,
            name = "MyTag",
            state = "Active",
            updatedAt = "2023-10-04T15:23:23Z",
            posters = emptyList()
        ))
        val telegramId = "1234567890"
        val title = "My lost item"
        val updatedAt = "2023-10-04T15:23:23Z"
        val userId = 1
        val userPhone = "0912345678"

        val getPosterByIdResponse = GetPosterByIdResponse(
            addresses = addresses,
            award = award,
            createdAt = 5L,
            description = description,
            id = id,
            images = images,
            specialType = specialType,
            state = state,
            status = status,
            tags = tags,
            telegramId = telegramId,
            title = title,
            updatedAt = updatedAt,
            userId = userId,
            userPhone = userPhone
        )

        assertEquals(addresses, getPosterByIdResponse.addresses)
        assertEquals(award, getPosterByIdResponse.award)
        assertEquals(description, getPosterByIdResponse.description)
        assertEquals(id, getPosterByIdResponse.id)
        assertEquals(images, getPosterByIdResponse.images)
        assertEquals(specialType, getPosterByIdResponse.specialType)
        assertEquals(state, getPosterByIdResponse.state)
        assertEquals(status, getPosterByIdResponse.status)
        assertEquals(tags, getPosterByIdResponse.tags)
        assertEquals(telegramId, getPosterByIdResponse.telegramId)
        assertEquals(title, getPosterByIdResponse.title)
        assertEquals(updatedAt, getPosterByIdResponse.updatedAt)
        assertEquals(userId, getPosterByIdResponse.userId)
        assertEquals(userPhone, getPosterByIdResponse.userPhone)
    }
}