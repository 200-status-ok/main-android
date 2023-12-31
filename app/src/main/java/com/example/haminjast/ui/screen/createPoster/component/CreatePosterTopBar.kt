@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.haminjast.ui.screen.createPoster.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.haminjast.R
import com.example.haminjast.ui.model.PosterStatus
import com.example.haminjast.ui.theme.VazirFont

@Composable
fun CreatePosterTopBar(
    onCloseClicked: () -> Unit = {},
    posterStatus: PosterStatus = PosterStatus.Lost,
    onLostClicked: () -> Unit = {},
    onFoundClicked: () -> Unit = {},
) {
//    TopAppBar(
//        title = {
//            Text(
//                text = stringResource(id = R.string.new_poster),
//                style = TextStyle(
//                    fontSize = 16.sp,
//                    fontFamily = VazirFont,
//                    fontWeight = FontWeight(700),
//                    color = Color(0xFF3A3A3A),
//                    textAlign = TextAlign.Right,
//                )
//            )
//        },
//        navigationIcon = {
//            IconButton(onClick = { onCloseClicked() }) {
//                Image(
//                    painter = painterResource(id = R.drawable.ic_cross),
//                    contentDescription = null
//                )
//            }
//        },
//        actions = {
//            LostOrFoundToggle(
//                value = posterStatus,
//                onLostClicked = onLostClicked,
//                onFoundClicked = onFoundClicked
//            )
//            Spacer(modifier = Modifier.size(12.dp))
//        },
//        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White),
//    )

    Row(
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Spacer(modifier = Modifier.size(16.dp))
        IconButton(onClick = { onCloseClicked() }) {
            Image(
                painter = painterResource(id = R.drawable.ic_cross),
                contentDescription = null
            )
        }
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = stringResource(id = R.string.new_poster),
            style = TextStyle(
                fontSize = 16.sp,
                fontFamily = VazirFont,
                fontWeight = FontWeight(700),
                color = Color(0xFF3A3A3A),
                textAlign = TextAlign.Right,
            )
        )
        Spacer(modifier = Modifier.weight(1f))
        LostOrFoundToggle(
            value = posterStatus,
            onLostClicked = onLostClicked,
            onFoundClicked = onFoundClicked
        )
        Spacer(modifier = Modifier.size(16.dp))
    }
}

@Composable
@Preview(locale = "fa")
fun CreatePosterTopBarPreview() {
    CreatePosterTopBar()
}