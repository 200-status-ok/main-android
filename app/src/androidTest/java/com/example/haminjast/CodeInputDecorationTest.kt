package com.example.haminjast

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.haminjast.ui.component.CodeInputDecoration
import org.junit.Rule
import org.junit.Test

class CodeInputDecorationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun codeInputDecorationTest() {
        val code = "123"
        val length = 4

        composeTestRule.setContent {
            CodeInputDecoration(code = code, length = length)
        }

        composeTestRule.onNodeWithText("1").assertExists()
        composeTestRule.onNodeWithText("2").assertExists()
        composeTestRule.onNodeWithText("3").assertExists()
    }
}