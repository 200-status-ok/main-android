package com.example.haminjast.ui.screen.ads

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import com.example.haminjast.ui.theme.PrimaryBlack
import com.example.haminjast.ui.theme.TagGray
import com.example.haminjast.ui.theme.VazirFont
import com.example.haminjast.ui.util.RTLPixel5Previews

@Composable
fun LocationSelector(
    modifier: Modifier = Modifier,
    text: String = "ستارخان",
    onSelectOnMapClicked: () -> Unit = {}
) {
    Row(
        modifier = modifier.background(Color.White, shape = RoundedCornerShape(4.dp)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.Start,
        ) {
            Icon(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .size(24.dp),
                painter = painterResource(id = R.drawable.ic_location),
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(start = 16.dp),
                text = "ستارخان",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = VazirFont,
                    fontWeight = FontWeight(400),
                    color = PrimaryBlack.copy(alpha = 0.6f),
                    textAlign = TextAlign.Right,
                )
            )
        }
        Button(
            modifier = Modifier
                .padding(end = 12.dp)
                .height(28.dp),
            onClick = onSelectOnMapClicked,
            contentPadding = PaddingValues(0.dp),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(containerColor = TagGray)
        ) {
            Text(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 1.dp),
                text = stringResource(R.string.select_on_map),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = VazirFont,
                    fontWeight = FontWeight(400),
                    color = PrimaryBlack,
                    textAlign = TextAlign.Right,
                )
            )
        }
    }
}


@RTLPixel5Previews
@Composable
fun LocationSelectorPreview() {
    LocationSelector(modifier = Modifier
        .fillMaxWidth()
        .height(42.dp)
    )
}