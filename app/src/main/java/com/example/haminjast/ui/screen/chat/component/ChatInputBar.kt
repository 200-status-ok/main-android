package com.example.haminjast.ui.screen.chat.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.haminjast.R
import com.example.haminjast.ui.theme.PrimaryBlack
import com.example.haminjast.ui.theme.VazirFont
import com.example.haminjast.ui.util.RTLPixel5Previews

@Composable
fun ChatInputBar(
    inputBarText: String = "", onInputBarTextChanged: (String) -> Unit = {},
    onSendClicked: () -> Unit = {}
) {
    Surface(
        color = Color.White,
        shadowElevation = 8.dp,
        tonalElevation = 8.dp
    ) {
        Row(
            modifier = Modifier
//                .height(64.dp)
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.Bottom
        ) {
            Button(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .size(48.dp),
                onClick = onSendClicked,
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlack),
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.ic_send),
                    contentDescription = null
                )
            }


            BasicTextField(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .background(Color.Transparent)
                    .heightIn(min = 64.dp, max = 128.dp)
                    .fillMaxWidth(),
                value = inputBarText,
                onValueChange = { s: String -> onInputBarTextChanged(s) },
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = VazirFont,
                    fontWeight = FontWeight(500),
                    color = PrimaryBlack.copy(alpha = 0.9f),
                    textAlign = TextAlign.Right,
                ),
                decorationBox = { innerTextField ->
                    Row(
                        modifier = Modifier
                            .padding(vertical = 8.dp)
                            .fillMaxWidth()
                            .border(
                                width = 1.5.dp,
                                color = PrimaryBlack.copy(alpha = 0.6f),
                                shape = RoundedCornerShape(16.dp)
                            ),
                        verticalAlignment = Alignment.Bottom
                    ) {

                        Row(
                            modifier = Modifier
                                .weight(1f)
                                .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp)
                                .align(CenterVertically),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            innerTextField()
                        }

                        Button(
                            modifier = Modifier
                                .padding(bottom = 6.dp)
                                .size(36.dp),
                            onClick = { /*TODO*/ },
                            contentPadding = PaddingValues(0.dp),
                            shape = CircleShape,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White,
                                contentColor = PrimaryBlack
                            )
                        ) {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                painter = painterResource(id = R.drawable.ic_camera),
                                contentDescription = null,
                                tint = PrimaryBlack.copy(alpha = 0.8f)
                            )
                        }
                        Button(
                            modifier = Modifier
                                .padding(end = 10.dp, bottom = 6.dp)
                                .size(36.dp),
                            onClick = { /*TODO*/ },
                            contentPadding = PaddingValues(0.dp),
                            shape = CircleShape,
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White,
                                contentColor = PrimaryBlack
                            )
                        ) {
                            Icon(
                                modifier = Modifier.size(24.dp),
                                painter = painterResource(id = R.drawable.ic_location),
                                contentDescription = null,
                                tint = PrimaryBlack.copy(alpha = 0.8f)
                            )
                        }
                    }

                }
            )
        }


    }
}

@RTLPixel5Previews
@Composable
fun ChatInputBarPreview() {
    ChatInputBar()
}