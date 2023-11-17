package com.example.haminjast

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.haminjast.ui.screen.common.RewardBar
import org.junit.Rule
import org.junit.Test

class RewardBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testRewardBar() {
        val reward = 100L
        val testContent = @Composable {
            RewardBar(reward = reward)
        }
        composeTestRule.setContent(testContent)
        composeTestRule.onNodeWithText("Reward", substring = true).assertIsDisplayed()
        composeTestRule.onNodeWithText("$reward تومان", substring = true).assertIsDisplayed()
    }
}