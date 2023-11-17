package com.example.haminjast

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.haminjast.ui.component.RegistrationCodeInput
import org.junit.Rule
import org.junit.Test

class RegistrationCodeInputTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun registrationCodeInputTest() {
        val codeLength = 4
        val initialCode = "1234"

        composeTestRule.setContent {
            RegistrationCodeInput(
                modifier = Modifier,
                codeLength = codeLength,
                initialCode = initialCode,
                onTextChanged = {}
            )
        }

        composeTestRule.onNodeWithText("1").assertExists()
        composeTestRule.onNodeWithText("2").assertExists()
        composeTestRule.onNodeWithText("3").assertExists()
        composeTestRule.onNodeWithText("4").assertExists()
    }
}
