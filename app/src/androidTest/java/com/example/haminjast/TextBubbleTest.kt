package com.example.haminjast

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.haminjast.ui.model.MessageContentType
import com.example.haminjast.ui.model.MessageStatus
import com.example.haminjast.ui.model.MessageUI
import com.example.haminjast.ui.screen.chat.component.MessageType
import com.example.haminjast.ui.screen.chat.component.TextBubble
import org.junit.Rule
import org.junit.Test

class TextBubbleTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun textBubbleRendersCorrectly() {
        val message = MessageUI(
            1,
            "Hello, World!",
            MessageContentType.Text,
            "1400/01/01",
            MessageStatus.Pending,
            MessageType.Incoming,
            1,
            1,
            1
        )

        composeTestRule.setContent {
            TextBubble(message = message)
        }

        composeTestRule.onNodeWithText("Hello, World!").assertExists()
    }
}
