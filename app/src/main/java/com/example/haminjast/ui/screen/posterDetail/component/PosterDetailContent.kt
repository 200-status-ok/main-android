@file:OptIn(ExperimentalFoundationApi::class)

package com.example.haminjast.ui.screen.posterDetail.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.haminjast.R
import com.example.haminjast.ui.screen.common.RewardBar
import com.example.haminjast.ui.screen.common.TitleDescription
import com.example.haminjast.ui.screen.posterDetail.ImageAlbum
import com.example.haminjast.ui.theme.PrimaryBlack
import com.example.haminjast.ui.theme.VazirFont

@Composable
fun PosterDetailContent(innerPadding: PaddingValues) {
    val configuration = LocalConfiguration.current

    val screenWidth = configuration.screenWidthDp.dp // todo remember
    CompositionLocalProvider(LocalOverscrollConfiguration provides null) {
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            ImageAlbum(width = screenWidth, height = (((screenWidth.value * 2) / 3) - 24).dp)
            Spacer(modifier = Modifier.size(16.dp))
            PosterDetailHeader()
            Spacer(modifier = Modifier.size(16.dp))
            RewardBar()
            Spacer(modifier = Modifier.size(16.dp))
            TitleDescription()
        }
    }
}

@Composable
fun PosterDetailHeader() {
    Column(modifier = Modifier.padding(start = 20.dp)) {
        Text(
            text = "اگزوز خاور",
            style = TextStyle(
                fontSize = 26.sp,
                fontFamily = VazirFont,
                fontWeight = FontWeight(700),
                color = PrimaryBlack,
                textAlign = TextAlign.Right,
            )
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "3 دقیقه پیش",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = VazirFont,
                    fontWeight = FontWeight(400),
                    color = PrimaryBlack.copy(alpha = 0.8f),
                    textAlign = TextAlign.Right,
                )
            )
            Spacer(modifier = Modifier.size(8.dp))
            Image(
                modifier = Modifier.size(5.dp),
                painter = painterResource(id = R.drawable.dot),
                contentDescription = null
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = "گم شده در ستارخان",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = VazirFont,
                    fontWeight = FontWeight(400),
                    color = PrimaryBlack,
                    textAlign = TextAlign.Right,
                )
            )
        }
    }
}

@Composable
@Preview(locale = "fa")
fun PosterDetailHeaderPreview() {
    PosterDetailHeader()
}