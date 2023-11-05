package com.example.haminjast.ui.screen.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.haminjast.ui.theme.PrimaryBlack
import com.example.haminjast.ui.theme.VazirFont
import com.example.haminjast.ui.util.RTLPixel5Previews

@Composable
fun TitleDescription(title: String, description: String? = null) {
    Column(modifier = Modifier.padding(start = 20.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = VazirFont,
                    fontWeight = FontWeight(600),
                    color = PrimaryBlack.copy(alpha = 0.9f),
                    textAlign = TextAlign.Right,
                )
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                modifier = Modifier.padding(bottom = 2.dp),
                text = ".................................................................................",
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
        description?.let {
            if (it.isEmpty()) return@let
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                modifier = Modifier.padding(end = 20.dp),
                text = it,
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = VazirFont,
                    fontWeight = FontWeight(400),
                    color = PrimaryBlack.copy(alpha = 0.9f),
                    textAlign = TextAlign.Right,
                )
            )
        }
    }
}

@RTLPixel5Previews
@Composable
fun TitleDescriptionPreview() {
    TitleDescription(title = "عنوان", description = "توضیحات")
}