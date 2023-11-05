package com.example.haminjast.ui.screen.createPoster.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.haminjast.R
import com.example.haminjast.ui.model.Contact
import com.example.haminjast.ui.screen.common.OutlinedCenteredIconButton
import com.example.haminjast.ui.theme.PrimaryBlack
import com.example.haminjast.ui.theme.VazirFont
import com.example.haminjast.ui.util.RTLPixel5Previews

@Composable
fun ContactsList(
    modifier: Modifier = Modifier,
    contacts: List<Contact>,
    onAddContactClicked: () -> Unit = {},
    onDeleteContactClicked: (Contact) -> Unit = {}
) {
    Column(modifier = modifier) {
        OutlinedCenteredIconButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(42.dp),
            onClick = { onAddContactClicked() },
            text = stringResource(R.string.add),
            icon = R.drawable.ic_plus
        )
        Spacer(modifier = Modifier.size(8.dp))
        for (contact in contacts) {
            ContactItem(contact = contact, onDeleteClicked = onDeleteContactClicked)
            Spacer(modifier = Modifier.size(8.dp))
        }
    }
}

@RTLPixel5Previews
@Composable
fun ContactsListPreview() {
    ContactsList(
        contacts = listOf(
            Contact("شماره تماس", "09123456789"),
            Contact("ایمیل", "")
        )
    )
}

@Composable
fun ContactItem(
    contact: Contact,
    onContactClicked: () -> Unit = {},
    onDeleteClicked: (Contact) -> Unit = {}
) { //todo copy on click
    Surface(
        color = PrimaryBlack.copy(alpha = 0.05f), shape = RoundedCornerShape(8.dp),
        border = BorderStroke(
            1.5.dp, color = PrimaryBlack.copy(alpha = 0.4f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(42.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.size(8.dp))
            IconButton(
                modifier = Modifier
                    .size(32.dp)
                    .padding(2.dp),
                onClick = { onDeleteClicked(contact) }
            ) {
                Icon(
                    modifier = Modifier.size(18.dp),
                    painter = painterResource(R.drawable.ic_cross_2),
                    contentDescription = null,
                    tint = PrimaryBlack.copy(alpha = 0.6f)
                )
            }
            Spacer(modifier = Modifier.size(2.dp))
            Text(
                text = contact.name,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = VazirFont,
                    fontWeight = FontWeight(400),
                    color = PrimaryBlack.copy(alpha = 0.8f),
                    textAlign = TextAlign.Right,
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = contact.value,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = VazirFont,
                    fontWeight = FontWeight(400),
                    color = PrimaryBlack.copy(alpha = 0.8f),
                    textAlign = TextAlign.Right,
                )
            )
            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}

@RTLPixel5Previews
@Composable
fun ContactItemPreview() {
    ContactItem(
        contact = Contact("شماره تماس", "09123456789")
    )
}
