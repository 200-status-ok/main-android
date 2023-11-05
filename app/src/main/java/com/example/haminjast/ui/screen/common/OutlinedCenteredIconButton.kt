package com.example.haminjast.ui.screen.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
fun OutlinedCenteredIconButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    icon: Int? = null
) {
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(
            1.5.dp, color = PrimaryBlack.copy(alpha = 0.4f)
        ),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
        onClick = { onClick() }) {
        icon?.let {
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(id = R.drawable.ic_plus),
                contentDescription = null,
                tint = PrimaryBlack.copy(alpha = 0.8f),
            )
            Spacer(modifier = Modifier.size(8.dp))
        }
        Text(
            text = text,
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = VazirFont,
                fontWeight = FontWeight(500),
                color = PrimaryBlack.copy(alpha = 0.8f),
                textAlign = TextAlign.Right,
            )
        )
    }
}

@RTLPixel5Previews
@Composable
fun OutlinedCenteredIconButtonPreview() {
    OutlinedCenteredIconButton(
        modifier = Modifier.fillMaxWidth().height(42.dp),
        onClick = {},
        text = "افزودن",
        icon = R.drawable.ic_plus
    )
}