package com.example.haminjast.ui.screen.createPoster.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.haminjast.R
import com.example.haminjast.ui.model.PosterStatus
import com.example.haminjast.ui.theme.FoundBlue
import com.example.haminjast.ui.theme.LostRed
import com.example.haminjast.ui.theme.PrimaryBlack
import com.example.haminjast.ui.theme.VazirFont
import com.example.haminjast.ui.util.RTLPixel5Previews

@Composable
fun LostOrFoundToggle(
    value: PosterStatus = PosterStatus.Lost,
    onLostClicked: () -> Unit = {},
    onFoundClicked: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .size(141.dp, 24.dp)
            .border(1.dp, PrimaryBlack, RoundedCornerShape(4.dp))
            .clip(RoundedCornerShape(4.dp)),
        verticalAlignment = CenterVertically,
    ) {
        TextButton(
            modifier = Modifier
                .width(70.dp),
            shape = RectangleShape,
            contentPadding = PaddingValues(0.dp), onClick = { onLostClicked() },
            colors = ButtonDefaults.textButtonColors(containerColor = if (value == PosterStatus.Lost) LostRed else Color.Transparent)
        ) {
            Text(
                modifier = Modifier.padding(bottom = 2.dp),
                text = stringResource(id = R.string.lost),
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = VazirFont,
                    fontWeight = FontWeight(500),
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                )
            )
        }
        Divider(
            modifier = Modifier
                .fillMaxHeight()
                .width(1.dp),
            color = PrimaryBlack
        )
        TextButton(
            modifier = Modifier
                .width(70.dp),
            shape = RectangleShape,
            contentPadding = PaddingValues(0.dp),
            onClick = { onFoundClicked() },
            colors = ButtonDefaults.textButtonColors(containerColor = if (value == PosterStatus.Found) FoundBlue else Color.Transparent)
        ) {
            Text(
                modifier = Modifier.padding(bottom = 2.dp),
                text = stringResource(id = R.string.found),
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = VazirFont,
                    fontWeight = FontWeight(500),
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                ),
            )
        }
    }
}

@RTLPixel5Previews
@Composable
fun LostOrFoundTogglePreview() {
    LostOrFoundToggle(value = PosterStatus.Lost)
}