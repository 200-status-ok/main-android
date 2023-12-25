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
import androidx.compose.material3.Switch
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
import com.example.haminjast.ui.theme.TagGray
import com.example.haminjast.ui.theme.VazirFont
import com.example.haminjast.ui.util.RTLPixel5Previews

@Composable
fun TitleSwitchToggle(
    modifier: Modifier = Modifier,
    title: String,
    isOn: Boolean,
    onToggle: () -> Unit = {}
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = VazirFont,
                fontWeight = FontWeight(600),
                color = PrimaryBlack,
                textAlign = TextAlign.Right,
            )
        )
        SwitchToggle(modifier = Modifier.size(48.dp, 28.dp), isOn = isOn, onToggle = { onToggle() })
    }
}

@Composable
fun SwitchToggle(
    modifier: Modifier = Modifier,
    isOn: Boolean,
    onToggle: (Boolean) -> Unit = {}
) { // TODO implement design
    Switch(modifier = modifier, checked = isOn, onCheckedChange = onToggle)
}

@RTLPixel5Previews
@Composable
fun SwitchTogglePreview() {
    SwitchToggle(isOn = false)
}

@RTLPixel5Previews
@Composable
fun TitleSwitchTogglePreview() {
    TitleSwitchToggle(
        modifier = Modifier.fillMaxWidth(),
        isOn = false,
        title = "فقط آگهی های با جایزه"
    )
}