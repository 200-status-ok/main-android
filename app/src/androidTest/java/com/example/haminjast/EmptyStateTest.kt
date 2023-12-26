//package com.example.haminjast
//
//import androidx.compose.ui.test.assertHasClickAction
//import androidx.compose.ui.test.assertIsDisplayed
//import androidx.compose.ui.test.junit4.createComposeRule
//import androidx.compose.ui.test.onNodeWithTag
//import androidx.compose.ui.test.onNodeWithText
//import androidx.test.ext.junit.runners.AndroidJUnit4
//import com.example.haminjast.ui.screen.EmptyState
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//
//
//@RunWith(AndroidJUnit4::class)
//class EmptyStateTest {
//
//    @get:Rule
//    val composeTestRule = createComposeRule()
//
//    @Test
//    fun emptyStateDisplaysCorrectly() {
//        composeTestRule.setContent {
//            EmptyState()
//        }
//
//        composeTestRule.onNodeWithText("برای دیدن آگهی های نشان شده و موجودی حساب باید وارد شوید")
//            .assertIsDisplayed()
//
//    }
//}