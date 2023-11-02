package com.example.haminjast.ui.screen.posterDetail.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.haminjast.R
import com.example.haminjast.ui.theme.ButtonBlue
import com.example.haminjast.ui.theme.PrimaryBlack
import com.example.haminjast.ui.theme.VazirFont
import com.example.haminjast.ui.util.RTLPixel5Previews

@Composable
fun ContactsBottomSheetContent(
    contacts: List<Pair<String, String>>? = null,
    onToggleBottomSheet: () -> Unit = {}
) {
    Divider(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.5.dp), color = PrimaryBlack.copy(alpha = 0.8f)
    )

    Row(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.size(20.dp))
            IconButton(onClick = { onToggleBottomSheet() }) {
                Image(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(R.drawable.ic_arrow_up),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = stringResource(R.string.contacts),
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = VazirFont,
                    fontWeight = FontWeight(500),
                    color = PrimaryBlack,
                    textAlign = TextAlign.Right,
                )
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            ChatButton()
            Spacer(modifier = Modifier.size(20.dp))
        }
    }

    Spacer(modifier = Modifier.size(16.dp))

    @Composable
    fun ContactsItem(contact: Pair<String, String>) {
        Row(
            modifier = Modifier
                .height(48.dp)
                .fillMaxWidth()
                .padding(start = 24.dp, end = 36.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = contact.first,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = VazirFont,
                    fontWeight = FontWeight(400),
                    color = PrimaryBlack.copy(alpha = 0.8f),
                    textAlign = TextAlign.Right,
                )
            )

            Text(
                text = contact.second,
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

    contacts?.let {
        LazyColumn {
            items(it, key = { contact -> contact.first }) { contact ->
                ContactsItem(contact)
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp), color = PrimaryBlack.copy(alpha = 0.2f)
                )
            }
            item {
                Spacer(modifier = Modifier.size(32.dp))
            }
        }
    }
}

@Composable
@RTLPixel5Previews
fun ContactsBottomSheetPreview() {
    ContactsBottomSheetContent(listOf())
}

@Composable
fun ChatButton() {
    Button(
        modifier = Modifier.size(120.dp, 36.dp),
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(containerColor = ButtonBlue),
        contentPadding = PaddingValues(0.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_chat),
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            modifier = Modifier.padding(bottom = 2.dp),
            text = "چت",
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = VazirFont,
                fontWeight = FontWeight(500),
                color = Color.White,
                textAlign = TextAlign.Right,
            )
        )
    }
}

@Composable
@RTLPixel5Previews
fun BlueBPreview() {
    ChatButton()
}