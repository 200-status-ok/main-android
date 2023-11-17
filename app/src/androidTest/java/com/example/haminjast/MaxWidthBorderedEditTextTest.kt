package com.example.haminjast

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import com.example.haminjast.ui.component.MaxWidthBorderedEditText
import org.junit.Rule
import org.junit.Test

class MaxWidthBorderedEditTextTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun maxBorderedEditTextTest() {
        val initialValue = "TestValue"
        val updatedValue = "UpdatedTestValue"

        composeTestRule.setContent {
            MaxWidthBorderedEditText(
                modifier = Modifier.testTag("MaxWidthBorderedEditText"),
                text = initialValue
            )
        }
        composeTestRule.onNodeWithText(initialValue).assertExists()
    }
}