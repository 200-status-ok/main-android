package com.example.haminjast

import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import com.example.haminjast.ui.model.UiConversation
import com.example.haminjast.ui.screen.chatslist.ChatsListScreen
import com.example.haminjast.ui.screen.chatslist.fakeConversationList
import org.junit.Rule
import org.junit.Test

class ChatsListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun chatsListScreenTest() {
        composeTestRule.setContent {
                    ChatsListScreen()
        }

        fakeConversationList.forEachIndexed { index, conversationItem ->
            composeTestRule
                .onNodeWithContentDescription("ChatItem_$index")
                .assertExists()

            // Verify if clicking on each item triggers the expected action
            composeTestRule
                .onNodeWithContentDescription("ChatItem_$index")
                .performClick()

        }
    }
}