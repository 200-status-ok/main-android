package com.example.haminjast

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.haminjast.ui.screen.common.TitleDescription
import org.junit.Rule
import org.junit.Test

class TitleDescriptionTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun titleDescriptionTest() {
        val title = "Test Title"
        val description = "Test Description"

        composeTestRule.setContent {
            TitleDescription(title = title, description = description)
        }

        composeTestRule.onNodeWithText(title).assertIsDisplayed()

        composeTestRule.onNodeWithText(description).assertIsDisplayed()
    }
}