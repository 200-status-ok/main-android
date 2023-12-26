package com.example.haminjast.ui.screen.ads

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.haminjast.R
import com.example.haminjast.ui.theme.PrimaryBlack
import com.example.haminjast.ui.theme.VazirFont
import com.example.haminjast.ui.util.RTLPixel5Previews

@Composable
fun OneRowSelector(
    modifier: Modifier = Modifier,
    selectedItem: Int = 0,
    onItemSelected: (Int) -> Unit = {}
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(R.string.poster_type),
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = VazirFont,
                fontWeight = FontWeight(600),
                color = PrimaryBlack,
                textAlign = TextAlign.Right,
            )
        )
        Row(modifier = Modifier, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            SelectorItem(
                text = stringResource(id = R.string.all),
                isSelected = selectedItem == 0,
                onItemSelected = { onItemSelected(0) })
            SelectorItem(
                text = stringResource(id = R.string.found),
                isSelected = selectedItem == 1,
                onItemSelected = { onItemSelected(1) })
            SelectorItem(
                text = stringResource(id = R.string.lost),
                isSelected = selectedItem == 2,
                onItemSelected = { onItemSelected(2) })
        }
    }
}

@Composable
fun SelectorItem(text: String, isSelected: Boolean = false, onItemSelected: () -> Unit = {}) {
    var modifier = Modifier
        .size(76.dp, 28.dp)
        .padding(top = 1.dp)
        .background(
            if (isSelected) Color.White else Color.Transparent,
            shape = RoundedCornerShape(8.dp)
        )

    if (isSelected) {
        modifier = modifier.border(
            1.5.dp,
            PrimaryBlack.copy(alpha = 0.9f),
            shape = RoundedCornerShape(8.dp)
        )
    }
    Text(
        modifier = modifier.clickable { onItemSelected() },
        color = PrimaryBlack.copy(alpha = if (isSelected) 0.9f else 0.6f),
        text = text,
        style = TextStyle(
            fontSize = 14.sp,
            fontFamily = VazirFont,
            fontWeight = FontWeight(if (isSelected) 600 else 400),
            color = PrimaryBlack,
            textAlign = TextAlign.Center,
        ),
    )
}

@RTLPixel5Previews
@Composable
fun SelectorItemPreview() {
    SelectorItem(text = "همه")
}

@RTLPixel5Previews
@Composable
fun OneRowSelectorPreview() {
    OneRowSelector(
        modifier = Modifier
            .fillMaxWidth()
            .height(28.dp)
    )
}