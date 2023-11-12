package com.example.haminjast.ui.screen.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.haminjast.R
import com.example.haminjast.ui.theme.DarkerLostRed
import com.example.haminjast.ui.theme.PrimaryBlack
import com.example.haminjast.ui.theme.VazirFont
import com.example.haminjast.ui.util.RTLPixel5Previews

@Composable
fun TagText(text: String, backgroundColor:Color) {
    val t = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                background = backgroundColor,
                fontSize = 12.sp,
                fontFamily = VazirFont,
                fontWeight = FontWeight(400),
                color = PrimaryBlack,
            )
        ) {
            append(" $text ")
        }
    }
    Text(
        modifier = Modifier.padding(2.dp),
        text = t,
    )
}

@RTLPixel5Previews
@Composable
fun TagPreview() {
    TagText(stringResource(R.string.my_poster), DarkerLostRed)
}