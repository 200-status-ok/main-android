package com.example.haminjast.data.model

import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Test

class GeneratePosterInfoResponseTest {

    @Test
    fun generatePosterInfoResponseTest() {
        // Create test data
        val colors = listOf("Red", "Green", "Blue")
        val description = "Test Description"
        val tags = listOf("Tag1", "Tag2", "Tag3")
        val titles = listOf("Title1", "Title2", "Title3")

        // Create a GeneratePosterInfoResponse instance for testing
        val generatePosterInfoResponse = GeneratePosterInfoResponse(colors, description, tags, titles)

        // Verify the values
        assertEquals(colors, generatePosterInfoResponse.colors)
        assertEquals(description, generatePosterInfoResponse.description)
        assertEquals(tags, generatePosterInfoResponse.tags)
        assertEquals(titles, generatePosterInfoResponse.titles)
    }

    // If you want to test JSON deserialization, you can use Mockito to mock the deserialization process
    @Test
    fun jsonDeserializationTest() {
        val json = """
            {
                "colors": ["Red", "Green", "Blue"],
                "description": "Test Description",
                "tags": ["Tag1", "Tag2", "Tag3"],
                "titles": ["Title1", "Title2", "Title3"]
            }
        """.trimIndent()

        val gson = Gson()

        val generatePosterInfoResponse = gson.fromJson(json, GeneratePosterInfoResponse::class.java)

        // Verify the deserialized values
        assertEquals(listOf("Red", "Green", "Blue"), generatePosterInfoResponse.colors)
        assertEquals("Test Description", generatePosterInfoResponse.description)
        assertEquals(listOf("Tag1", "Tag2", "Tag3"), generatePosterInfoResponse.tags)
        assertEquals(listOf("Title1", "Title2", "Title3"), generatePosterInfoResponse.titles)
    }
}
