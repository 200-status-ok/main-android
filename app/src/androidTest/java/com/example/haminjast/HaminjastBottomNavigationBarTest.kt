package com.example.haminjast

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.haminjast.ui.screen.common.HaminjastBottomNavigationBarItem
import junit.framework.TestCase.assertTrue
import org.junit.Rule
import org.junit.Test

class HaminjastBottomNavigationBarTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun haminjastBottomNavigationBarTest() {

        val label = "Ads"
        val icon = R.drawable.ic_haminjast
        var clicked = false
        val onItemClicked: () -> Unit = { clicked = true }
        composeTestRule
        val testContent = @Composable {
            HaminjastBottomNavigationBarItem(
                label = label,
                icon = icon,
                selected = false,
                onItemClicked = onItemClicked
            )
        }
        composeTestRule.setContent(testContent)
        assertTrue(!clicked)
    }
}