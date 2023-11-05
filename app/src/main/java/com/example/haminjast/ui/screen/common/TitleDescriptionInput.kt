package com.example.haminjast.ui.screen.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.haminjast.ui.theme.PrimaryBlack
import com.example.haminjast.ui.theme.VazirFont
import com.example.haminjast.ui.util.RTLPixel5Previews

@Composable
fun TitleDescriptionInput(
    title: String,
    description: String,
    inputFieldHeight: Dp,
    value: String = "",
    onValueChanged: (String) -> Unit = {},
    cursorAlignment: Alignment = Alignment.CenterStart,
) {
    Column(modifier = Modifier.padding(start = 20.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = VazirFont,
                    fontWeight = FontWeight(500),
                    color = PrimaryBlack.copy(alpha = 0.9f),
                    textAlign = TextAlign.Right,
                )
            )
            Spacer(modifier = Modifier.width(24.dp))
            Text(
                text = "...........................................................................",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = VazirFont,
                    fontWeight = FontWeight(600),
                    color = PrimaryBlack.copy(alpha = 0.4f),
                    textAlign = TextAlign.Left,
                    letterSpacing = 2.8.sp,
                ),
                maxLines = 1
            )
        }
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            modifier = Modifier.padding(end = 20.dp),
            text = description,
            style = TextStyle(
                fontSize = 12.sp,
                fontFamily = VazirFont,
                fontWeight = FontWeight(400),
                color = PrimaryBlack.copy(alpha = 0.8f),
                textAlign = TextAlign.Right,
            )
        )
        Spacer(modifier = Modifier.size(8.dp))
        BasicTextField(
            modifier = Modifier
                .background(Color.Transparent)
                .height(inputFieldHeight)
                .fillMaxWidth()
                .padding(end = 20.dp)
                .border(
                    width = 1.5.dp,
                    color = PrimaryBlack.copy(alpha = 0.4f),
                    shape = RoundedCornerShape(8.dp)
                ),
            value = value,
            onValueChange = { s: String -> onValueChanged(s) },
            textStyle = TextStyle(
                fontSize = 14.sp,
                fontFamily = VazirFont,
                fontWeight = FontWeight(500),
                color = PrimaryBlack.copy(alpha = 0.9f),
                textAlign = TextAlign.Right,
            ),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 10.dp),
                    contentAlignment = cursorAlignment
                ) {
                    innerTextField()
                }

            }
        )
    }
}

@RTLPixel5Previews
@Composable
fun TitleDescriptionInputPreview() {
    TitleDescriptionInput(title = "عنوان", description = "توضیحات", inputFieldHeight = 148.dp)
}