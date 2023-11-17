package com.example.haminjast

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.assertHasClickAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.haminjast.ui.screen.ads.ErrorMessage
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalComposeUiApi::class)
class ErrorMessageTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun errorMessageContent() {
        composeTestRule.setContent {
            ErrorMessage(
                message = "An error occurred",
                onClickRetry = {}
            )
        }

        composeTestRule.onNodeWithText("An error occurred", substring = true)
            .assertExists()

        composeTestRule.onNodeWithText("retry")
            .assertExists()
            .assertHasClickAction()
    }

    @Test
    fun errorMessageClickRetry() {
        var retryClicked = false

        composeTestRule.setContent {
            ErrorMessage(
                message = "An error occurred",
                onClickRetry = { retryClicked = true }
            )
        }

        composeTestRule.onNodeWithText("retry")
            .performClick()

        assert(retryClicked)
    }
}