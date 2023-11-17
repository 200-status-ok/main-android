package com.example.haminjast

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
import com.example.haminjast.ui.model.PosterStatus
import com.example.haminjast.ui.model.UiPoster
import com.example.haminjast.ui.screen.posterDetail.component.PosterDetailContent
import org.junit.Rule
import org.junit.Test

class PosterDetailTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testPosterDetailContent() {
        val poster = UiPoster(
            title = "The Shawshank Redemption",
            status = PosterStatus.Found,
            timeCreatedTimeStamp = 53635345635,
            vicinity = "Amsterdam, North Holland, Netherlands",
            reward = 100L,
            description = "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
            lat = 1.1,
            lng = 1.1,
            id =1,
            issuerId = 1
        )
        val testContent = @Composable {
            PosterDetailContent(innerPadding = PaddingValues(16.dp), poster = poster)
        }
        composeTestRule.setContent(testContent)
        composeTestRule.onNodeWithText("The Shawshank Redemption", substring = true).assertIsDisplayed()
    }
}