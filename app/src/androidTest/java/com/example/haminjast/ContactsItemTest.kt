package com.example.haminjast

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.haminjast.ui.model.Contact
import com.example.haminjast.ui.theme.PrimaryBlack
import com.example.haminjast.ui.theme.VazirFont
import org.junit.Rule
import org.junit.Test

class ContactsItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testContactsItem() {
        // Given a Contact object
        val contact = Contact(name = "John Doe", value = "john.doe@example.com")

        composeTestRule.setContent {
            MaterialTheme {
                ContactsItem(contact = contact)
            }
        }
        composeTestRule.onNodeWithText("John Doe").assertIsDisplayed()
        composeTestRule.onNodeWithText("john.doe@example.com").assertIsDisplayed()
    }

    @Composable
    fun ContactsItem(contact: Contact) {
        Row(
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth()
                .padding(start = 24.dp, end = 36.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = contact.name,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = VazirFont,
                    fontWeight = FontWeight(400),
                    color = PrimaryBlack.copy(alpha = 0.8f),
                    textAlign = TextAlign.Right,
                )
            )

            Text(
                text = contact.value,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = VazirFont,
                    fontWeight = FontWeight(400),
                    color = PrimaryBlack.copy(alpha = 0.9f),
                    textAlign = TextAlign.Right,
                )
            )
        }
    }
}