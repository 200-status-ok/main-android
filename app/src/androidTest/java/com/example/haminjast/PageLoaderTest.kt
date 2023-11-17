package com.example.haminjast

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChild
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onParent
import androidx.compose.ui.unit.dp
import com.example.haminjast.ui.screen.ads.PageLoader
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalComposeUiApi::class)
class PageLoaderTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun pageLoaderContent() {
        composeTestRule.setContent {
            PageLoader()
        }

        composeTestRule.onNodeWithText("Loading...", substring = true)
            .assertExists()

    }
}