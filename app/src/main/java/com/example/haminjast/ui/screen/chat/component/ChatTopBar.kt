package com.example.haminjast.ui.screen.chat.component

import androidx.compose.foundation.Image
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.haminjast.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatTopBar(onBackClicked: () -> Unit = {}, onMenuClicked: () -> Unit = {}) {
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(onClick = { onBackClicked() }) {
                Image(painter = painterResource(id = R.drawable.ic_back), contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = { onMenuClicked() }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_3_dots_menu),
                    contentDescription = null
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White),
    )
}