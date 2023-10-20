package com.example.haminjast.ui.screen

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.haminjast.R

@Composable
fun MeScreen(onLoginClicked: () -> Unit = {}) {
    Button(onClick = {
        onLoginClicked()
    }) {
        Text(text = stringResource(id = R.string.login))
    }
}

