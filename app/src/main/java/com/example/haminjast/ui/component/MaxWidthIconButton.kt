package com.example.haminjast.ui.component

import android.service.autofill.OnClickAction
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.haminjast.R
import com.example.haminjast.ui.theme.PrimaryBlack

@Composable
fun MaxWidthIconButton(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int? = null,
    text: String,
    backgroundColor: Color = PrimaryBlack,
    contentColor: Color = Color.White,
    onClick : () -> Unit
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        shape = RoundedCornerShape(6.dp),
        colors = ButtonDefaults.buttonColors(containerColor = backgroundColor, contentColor = contentColor),
    ) {
        val iconSize = 18.dp
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.size(iconSize))
            Text(
                modifier = Modifier
                    .weight(1f),
                text = text,
                textAlign = TextAlign.Center,
                fontSize = 14.sp
            )
            if (icon != null) {
                Icon(
                    modifier = Modifier.size(iconSize),
                    painter = painterResource(id = R.drawable.ic_google),
                    contentDescription = null,
                )
            } else {
                Spacer(modifier = Modifier.size(iconSize))
            }
        }
    }
}