package com.example.haminjast

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.haminjast.ui.screen.meScreen.MyPosterSaveHeader
import org.junit.Rule
import org.junit.Test

class MyPosterSaveHeaderTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun myPosterSaveHeader_displaysCount() {
        val count = 10

        composeTestRule.setContent {
            MyPosterSaveHeader(count = count)
        }

        composeTestRule
            .onNodeWithText("تعداد : $count")
            .assertExists()
    }
}