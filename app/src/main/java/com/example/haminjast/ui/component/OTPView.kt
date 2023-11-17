package com.example.haminjast.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.haminjast.R
import com.example.haminjast.ui.screen.login.LoginViewModel.OTPState
import com.example.haminjast.ui.theme.PrimaryBlack
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds


@Composable
fun RegistrationCodeInput(
    modifier: Modifier = Modifier,
    codeLength: Int,
    initialCode: String,
    onTextChanged: (String) -> Unit
) {
    val code = remember(initialCode) {
        mutableStateOf(TextFieldValue(initialCode, TextRange(initialCode.length)))
    }

    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        BasicTextField(
            value = code.value,
            onValueChange = {
                code.value = it
                onTextChanged(code.value.text)
            },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            decorationBox = {
                CodeInputDecoration(code.value.text, codeLength)
            }
        )
    }
}

@Composable
fun CodeInputDecoration(code: String, length: Int) {
    Box(modifier = Modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            for (i in 0 until length) {
                val text = if (i < code.length) code[i].toString() else ""
                CodeEntry(text)
                if (i < length - 1) {
                    Divider(
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(top = 16.dp, bottom = 16.dp)
                            .width(1.dp),
                        color = PrimaryBlack.copy(alpha = 0.2f)
                    )
                }
            }
        }
    }
}

@Composable
fun CodeEntry(text: String) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .width(28.dp)
            .fillMaxHeight()
            .padding(top = 4.dp, bottom = 4.dp),
        contentAlignment = Alignment.Center
    ) {
        val color = animateColorAsState(
            targetValue = if (text.isEmpty()) Color.Gray.copy(alpha = .8f)
            else Color.Blue.copy(.8f), label = ""
        )
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = text,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = PrimaryBlack,
        )
        Box(
            Modifier
                .align(Alignment.BottomCenter)
                .padding(start = 0.dp, end = 0.dp, bottom = 8.dp)
                .height(1.5.dp)
                .fillMaxWidth()
                .background(color.value)
        )
    }
}


@Composable
fun OTPView(
    modifier: Modifier = Modifier,
    otpState: OTPState = OTPState.NOT_REQUESTED,
    onSendOTPClicked: () -> Unit = {},
    onTextChanged: (String) -> Unit = {}
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .border(width = 2.dp, color = PrimaryBlack, shape = RoundedCornerShape(6.dp)),
        color = Color.White,
        shape = RoundedCornerShape(6.dp)
    ) {
        Row {
            RegistrationCodeInput(
                modifier = Modifier.fillMaxWidth(0.75f),
                codeLength = 6,
                initialCode = "",
                onTextChanged = onTextChanged
            )
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(2.dp), color = PrimaryBlack
            )
            SendOTPButton(modifier = Modifier.fillMaxSize(), otpState, onSendOTPClicked)
        }
    }
}

@Composable
fun SendOTPButton(
    modifier: Modifier = Modifier,
    otpState: OTPState,
    onSendOTPClicked: () -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        when (otpState) {
            OTPState.NOT_REQUESTED -> {
                TextButton(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = PrimaryBlack.copy(alpha = 0.15f)),
                    onClick = {
                        onSendOTPClicked()
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.send),
                        color = PrimaryBlack.copy(alpha = 0.8f)
                    )
                }
            }

            OTPState.REQUESTED -> {
                CircularProgressIndicator(modifier = Modifier.size(32.dp))
            }

            OTPState.SENT -> {
                var remainingTime by remember { mutableIntStateOf(120) }
                LaunchedEffect(Unit) {
                    while (true) {
                        delay(1.seconds)
                        remainingTime--
                    }
                }
                Text(
                    modifier = Modifier,
                    text = secondsToMinuteAndSecondsString(remainingTime),
                    color = PrimaryBlack.copy(alpha = 0.8f),
                    textAlign = TextAlign.Center,
                    fontSize = 18.sp
                )
            }
        }
    }
}

fun secondsToMinuteAndSecondsString(seconds: Int): String {
    val minutes = "0${seconds / 60}"
    val remainingSeconds = if ((seconds % 60) < 10) "0${seconds % 60}" else seconds % 60
    return "$minutes:$remainingSeconds"
}

@Preview(locale = "fa")
@Composable
fun OTPViewP() {
    OTPView(Modifier.height(60.dp), otpState = OTPState.SENT)
}