package com.example.haminjast.ui.screen.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
fun TitleDescription() {
    Column(modifier = Modifier.padding(start = 20.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "توضیحات",
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
                text = ".......................................",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = VazirFont,
                    fontWeight = FontWeight(600),
                    color = PrimaryBlack.copy(alpha = 0.4f),
                    textAlign = TextAlign.Right,
                    letterSpacing = 2.8.sp,
                ),
                maxLines = 1
            )
        }
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            modifier = Modifier.padding(end = 20.dp),
            text = "یکی از مشکلاتی که مشکلان شصیشسطز لفذذر زی بیشتر افراد با آن مواجه هستند. یکی از مشکلاتی که بیشتر افراد با آن مواجه هستند.یکی از مشکلاتی که مشکلان شصیشسطز لفذذر زی بیشتر افراد با آن مواجه هستند. یکی از مشکلاتی که بیشتر افراد با آن مواجه هستند.یکی از مشکلاتی که مشکلان شصیشسطز لفذذر زی بیشتر افراد با آن مواجه هستند.\n یکی از مشکلاتی که بیشتر افراد با آن مواجه هستند.یکی از مشکلاتی که مشکلان شصیشسطز لفذذر زی بیشتر افراد با آن مواجه هستند. یکی از مشکلاتی که بیشتر افراد با آن مواجه هستند.",
            style = TextStyle(
                fontSize = 12.sp,
                fontFamily = VazirFont,
                fontWeight = FontWeight(400),
                color = PrimaryBlack.copy(alpha = 0.8f),
                textAlign = TextAlign.Right,
            )
        )
    }
}

@RTLPixel5Previews
@Composable
fun TitleDescriptionPreview() {
    TitleDescription()
}