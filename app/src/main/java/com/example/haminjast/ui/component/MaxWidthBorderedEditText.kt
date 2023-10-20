package com.example.haminjast.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.haminjast.R
import com.example.haminjast.ui.theme.PrimaryBlack

@Composable
fun MaxWidthBorderedEditText(
    modifier: Modifier = Modifier,
    text: String = "",
    onValueChanged: (String) -> Unit={}
) {
    BasicTextField(
        modifier = modifier
            .background(Color.White)
            .height(70.dp)
            .fillMaxWidth()
            .border(width = 2.dp, color = PrimaryBlack, shape = RoundedCornerShape(6.dp)),
        value = text,
        onValueChange = { s: String -> onValueChanged(s) },
        textStyle = TextStyle.Default.copy(fontSize = 14.sp),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                if (text.isEmpty()) {
                    Text(
                        modifier = Modifier,
                        text = stringResource(id = R.string.email_or_phone_number),
                        fontSize = 14.sp,
                        color = PrimaryBlack.copy(alpha = 0.6f)
                    )
                }
                innerTextField()
            }
        }
    )
}

@Preview(locale = "fa")
@Composable
fun MaxWidthBorderedEditTextPreview() {
    MaxWidthBorderedEditText()
}

