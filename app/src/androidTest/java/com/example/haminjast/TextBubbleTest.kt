package com.example.haminjast

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.haminjast.ui.model.UiMessage
import com.example.haminjast.ui.screen.chat.component.MessageType
import com.example.haminjast.ui.screen.chat.component.TextBubble
import org.junit.Rule
import org.junit.Test

class TextBubbleTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun textBubbleRendersCorrectly() {
        val message = UiMessage(
            id = 1,
            text = "Hello, World!",
            type = MessageType.Incoming,
            date = "2023-11-17",
            time = "12:34 PM"
        )

        composeTestRule.setContent {
            TextBubble(message = message)
        }

        composeTestRule.onNodeWithText("Hello, World!").assertExists()
    }
}
