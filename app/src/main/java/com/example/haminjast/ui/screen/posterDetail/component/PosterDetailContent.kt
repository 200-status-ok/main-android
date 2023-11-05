@file:OptIn(ExperimentalFoundationApi::class)

package com.example.haminjast.ui.screen.posterDetail.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.haminjast.R
import com.example.haminjast.ui.model.PosterStatus
import com.example.haminjast.ui.model.UiPoster
import com.example.haminjast.ui.screen.common.RewardBar
import com.example.haminjast.ui.screen.common.TitleDescription
import com.example.haminjast.ui.theme.DarkerLostRed
import com.example.haminjast.ui.theme.FoundBlue
import com.example.haminjast.ui.theme.PrimaryBlack
import com.example.haminjast.ui.theme.VazirFont

@Composable
fun PosterDetailContent(innerPadding: PaddingValues, poster: UiPoster? = null) {
    val configuration = LocalConfiguration.current

    val screenWidth = remember {
        configuration.screenWidthDp.dp
    }

    CompositionLocalProvider(LocalOverscrollConfiguration provides null) {
        poster?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
            ) {
                ImageAlbum(
                    width = screenWidth,
                    height = (((screenWidth.value * 2) / 3) - 24).dp,
                    imageUrls = it.imageUrls
                )
                Spacer(modifier = Modifier.size(16.dp))
                PosterDetailHeader(
                    title = it.title,
                    posterStatus = it.status,
                    timeCreated = it.timeCreated,
                    vicinity = it.vicinity
                )
                Spacer(modifier = Modifier.size(16.dp))
                it.reward?.let { it1 ->
                    RewardBar(it1)
                    Spacer(modifier = Modifier.size(16.dp))
                }
                TitleDescription(stringResource(id = R.string.description), it.description)
                Spacer(modifier = Modifier.size(16.dp))
            }
        }
    }
}

@Composable
fun PosterDetailHeader(
    title: String,
    posterStatus: PosterStatus,
    timeCreated: String,
    vicinity: String
) {
    Column(modifier = Modifier.padding(start = 20.dp)) {
        Text(
            text = title,
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
                text = timeCreated,
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
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = if (posterStatus == PosterStatus.Lost) DarkerLostRed else FoundBlue)) {
                        append(stringResource(id = posterStatus.value))
                    }
                    withStyle(style = SpanStyle(color = PrimaryBlack.copy(alpha = 0.8f))) {
                        append(" در ")
                    }
                    withStyle(style = SpanStyle(color = PrimaryBlack.copy(alpha = 1f))) {
                        append(vicinity)
                    }
                },
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
    PosterDetailHeader(
        title = "دوچرخه یک سرنشینه",
        posterStatus = PosterStatus.Lost,
        timeCreated = "سه دقیقه پیش",
        vicinity = "میدان ونک"
    )
}