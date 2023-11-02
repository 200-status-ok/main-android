package com.example.haminjast.ui.screen.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.haminjast.R
import com.example.haminjast.data.datastore.LoginDataStore
import com.example.haminjast.data.repository.LoginRepository
import com.example.haminjast.ui.component.MaxWidthBorderedEditText
import com.example.haminjast.ui.component.MaxWidthIconButton
import com.example.haminjast.ui.component.OTPView
import com.example.haminjast.ui.theme.PrimaryBlack

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = viewModel(
        factory = LoginViewModelFactory(
            LoginRepository,
            LoginDataStore(LocalContext.current)
        )
    )
) {
    val otpState: LoginViewModel.OTPState by viewModel.otpState.collectAsStateWithLifecycle()
    val userName: String by viewModel.userName.collectAsStateWithLifecycle()
    val otp: String by viewModel.otp.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 32.dp, end = 32.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Row(
                modifier = Modifier.padding(top = 32.dp),
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.ic_person),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(
                    modifier = Modifier.weight(1f),
                    text = stringResource(id = R.string.login),
                    style = TextStyle(
                        fontSize = 32.sp,
//                    fontFamily = FontFamily(Font(R.font.inter)),
                        fontWeight = FontWeight(700),
                        color = Color(0xFF3A3A3A),

                        textAlign = TextAlign.Center,
                        letterSpacing = 1.92.sp,
                    ),
                    textAlign = TextAlign.Start,

                    )
                Icon(
                    modifier = Modifier.width(200.dp),
                    painter = painterResource(R.drawable.dots_line),
                    contentDescription = null
                )
            }
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = stringResource(id = R.string.login_to_continue),
                style = TextStyle(
                    fontSize = 24.sp,
//                fontFamily = FontFamily(Font(R.font.inter)),
                    fontWeight = FontWeight(400),
                    color = Color(0xCC3A3A3A),
                    textAlign = TextAlign.Start,
                )
            )
            Spacer(
                modifier = Modifier
                    .height(48.dp)
                    .fillMaxWidth()
            )

            MaxWidthBorderedEditText(modifier = Modifier.height(52.dp), userName,
                onValueChanged = {
                    viewModel.onUserNameChanged(it)
                }
            )
            Spacer(modifier = Modifier.size(8.dp))
            OTPView(modifier = Modifier.height(52.dp), otpState,
                onSendOTPClicked = {
                viewModel.sendOTP(userName)
            },
                onTextChanged = {
                viewModel.onOtpChanged(it)
            })
        }

        Column(Modifier.padding(bottom = 64.dp)) {
            MaxWidthIconButton(
                modifier = Modifier.height(52.dp),
                text = stringResource(id = R.string.login),
                onClick = {
                    viewModel.verifyOTP(userName,otp)
                }
            )
            Spacer(modifier = Modifier.size(16.dp))
            MaxWidthIconButton(
                modifier = Modifier.height(52.dp),
                text = stringResource(id = R.string.login_with_google),
                icon = R.drawable.ic_google,
                backgroundColor = Color.White,
                contentColor = PrimaryBlack,
                onClick = {

                }
            )
        }
    }
}


@Preview(locale = "fa", backgroundColor = 0xFFF9FFF6, showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}