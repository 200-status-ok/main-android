package com.example.haminjast.ui.screen.posterDetail.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.haminjast.R

@Composable
fun ImageAlbum(width: Dp, height: Dp) {
    val x = height.value
    val y = (height.value / 2) - 4

    @Composable
    fun Img(size: Dp) {
        Image(
            modifier = Modifier
                .size(size)
                .clip(RoundedCornerShape(6.dp)),
            painter = painterResource(R.drawable.bicycle),
            contentDescription = null
        )
    }

    Row {
        Spacer(modifier = Modifier.size(16.dp))
        Img(x.dp)
        Spacer(modifier = Modifier.size(8.dp))
        Column {
            Img(y.dp)
            Spacer(modifier = Modifier.size(8.dp))
            Img(y.dp)
        }
    }
}

@Composable
@Preview(locale = "fa")
fun ImageAlbumPreview() {
    val configuration = LocalConfiguration.current

    val screenWidth = configuration.screenWidthDp.dp
    ImageAlbum(width = screenWidth, height = (((screenWidth.value * 2) / 3) - 24).dp)
}