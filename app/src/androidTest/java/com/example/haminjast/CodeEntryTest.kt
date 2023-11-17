package com.example.haminjast

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.haminjast.ui.component.CodeEntry
import org.junit.Rule
import org.junit.Test


class CodeEntryTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun codeEntryTest() {
        composeTestRule.setContent {
            CodeEntry("A")
        }

        composeTestRule.onNodeWithText("A").assertExists()
    }
}