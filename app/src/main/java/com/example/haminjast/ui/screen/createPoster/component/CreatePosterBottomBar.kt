package com.example.haminjast.ui.screen.createPoster.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.example.haminjast.ui.theme.VazirFont
import com.example.haminjast.ui.util.RTLPixel5Previews

@Composable
fun CreatePosterBottomBar(
    onClickYes : () -> Unit = {},
    onClickNo : () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(42.dp),
            onClick = onClickYes,
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryBlack),
            contentPadding = PaddingValues(0.dp),

            ) {
            Row(
                Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(bottom = 2.dp),
                    text = stringResource(R.string.confirm),
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontFamily = VazirFont,
                        fontWeight = FontWeight(500),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Right,
                    )
                )
                Spacer(modifier = Modifier.size(8.dp))
                Image(
                    modifier = Modifier.size(18.dp),
                    painter = painterResource(R.drawable.ic_confirm_tick),
                    contentDescription = null
                )
            }
        }
        Spacer(modifier = Modifier.size(8.dp))
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(42.dp),
            onClick = onClickNo,
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(width = 1.5.dp, color = PrimaryBlack.copy(alpha = 0.8f)),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
            contentPadding = PaddingValues(0.dp),
        ) {
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(id = R.drawable.ic_cross),
                contentDescription = null,
                tint = PrimaryBlack.copy(alpha = 0.8f)
            )
        }
    }
}

@RTLPixel5Previews
@Composable
fun CreatePosterBottomBarPreview() {
    CreatePosterBottomBar()
}