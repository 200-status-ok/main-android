package com.example.haminjast

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.unit.dp
import com.example.haminjast.ui.screen.posterDetail.component.ImageAlbum
import org.junit.Rule
import org.junit.Test

class ImageAlbumTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun ImageAlbum_OneImage() {

        val imageUrl = "https://example.com/image1.jpg"
        composeTestRule.setContent {
                ImageAlbum(width = 100.dp, height = 200.dp, imageUrls = listOf(imageUrl))
        }

        composeTestRule.onNodeWithContentDescription("")
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun ImageAlbum_ThreeImages() {

        val imageUrl1 = "https://example.com/image1.jpg"
        val imageUrl2 = "https://example.com/image2.jpg"
        val imageUrl3 = "https://example.com/image3.jpg"
        composeTestRule.setContent {
                ImageAlbum(width = 100.dp, height = 200.dp, imageUrls = listOf(imageUrl1, imageUrl2, imageUrl3))
        }

        composeTestRule.onAllNodesWithContentDescription("")
            .assertCountEquals(3)
    }
}