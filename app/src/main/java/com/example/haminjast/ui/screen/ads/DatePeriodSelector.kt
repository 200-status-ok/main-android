package com.example.haminjast.ui.screen.ads

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.haminjast.R
import com.example.haminjast.ui.theme.PrimaryBlack
import com.example.haminjast.ui.theme.VazirFont
import com.example.haminjast.ui.util.RTLPixel5Previews

@Composable
fun DatePeriodSelector(
    modifier: Modifier = Modifier,
    startText: String,
    endText: String,
    onSelectStartDateClicked: () -> Unit = {},
    onSelectEndDateClicked: () -> Unit = {}
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.from),
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = VazirFont,
                fontWeight = FontWeight(600),
                color = PrimaryBlack,
                textAlign = TextAlign.Right,
            )
        )
        DateSelector(onSelectDateClicked = onSelectStartDateClicked, text = startText)
        Text(
            text = stringResource(R.string.to),
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = VazirFont,
                fontWeight = FontWeight(600),
                color = PrimaryBlack,
                textAlign = TextAlign.Right,
            )
        )
        DateSelector(onSelectDateClicked = onSelectEndDateClicked, text = endText)
    }
}

@Composable
fun DateSelector(text: String, onSelectDateClicked: () -> Unit = {}) {
    Button(
        modifier = Modifier
            .size(128.dp, 42.dp),
        onClick = onSelectDateClicked,
        contentPadding = PaddingValues(0.dp),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
    ) {
        Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(24.dp),
                painter = painterResource(id = R.drawable.ic_calendar),
                contentDescription = null,
                tint = PrimaryBlack
            )
            Text(
                modifier = Modifier.weight(1f).padding(start = 12.dp, end = 12.dp),
                text = text,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = VazirFont,
                    fontWeight = FontWeight(400),
                    color = PrimaryBlack.copy(alpha = 0.6f),
                    textAlign = TextAlign.Center,
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@RTLPixel5Previews
@Composable
fun DatePeriodSelectorPreview() {
    DatePeriodSelector(modifier = Modifier.fillMaxWidth(), startText = "شروع", endText = "پایان")
}