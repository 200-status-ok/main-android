package com.example.haminjast

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.haminjast.ui.model.PosterStatus
import com.example.haminjast.ui.screen.createPoster.component.LostOrFoundToggle
import org.junit.Rule
import org.junit.Test

class LostOrFoundToggleTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testLostOrFoundToggle() {
        composeTestRule.setContent {
            LostOrFoundToggle(value = PosterStatus.Lost)
        }


        composeTestRule.onNodeWithText("گم شده", useUnmergedTree = true).assertExists()
        composeTestRule.onNodeWithText("پیدا شده", useUnmergedTree = true).assertExists()

    }
}