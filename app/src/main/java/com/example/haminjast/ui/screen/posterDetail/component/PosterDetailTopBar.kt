@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.haminjast.ui.screen.posterDetail.component

import androidx.compose.foundation.Image
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.haminjast.R

@Composable
fun PosterDetailTopBar(
    onBackClicked: () -> Unit = {},
    onBookMarkClicked: () -> Unit = {},
    onMenuClicked: () -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior=TopAppBarDefaults.pinnedScrollBehavior()
) {
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(onClick = { onBackClicked() }) {
                Image(painter = painterResource(id = R.drawable.ic_back), contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = { onBookMarkClicked() }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_bookmark),
                    contentDescription = null
                )
            }
            IconButton(onClick = { onMenuClicked() }) {
                Image(
                    painter = painterResource(id = R.drawable.ic_3_dots_menu),
                    contentDescription = null
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White),
        scrollBehavior = scrollBehavior
    )
}

@Composable
@Preview(locale = "fa")
fun PosterDetailTopBarPreview() {
    PosterDetailTopBar()
}