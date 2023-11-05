package com.example.haminjast.ui.screen.posterDetail.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun ImageAlbum(width: Dp, height: Dp, imageUrls: List<String>? = null) {
    val x = height.value
    val y = (height.value / 2) - 4

    @Composable
    fun Img(modifier: Modifier = Modifier, url: String) {
        AsyncImage(
            modifier = modifier
                .clip(RoundedCornerShape(6.dp)),
            model = url,
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
    }

    when (imageUrls?.size) {
        null, 0 -> {

        }

        1 -> {
            Row(modifier = Modifier.padding(horizontal = 16.dp)) {
                Img(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(x.dp), imageUrls[0]
                )
            }
        }

        2 -> {
            Row {
                Spacer(modifier = Modifier.size(16.dp))
                Img(modifier = Modifier.size(x.dp, x.dp), imageUrls[0])
                Spacer(modifier = Modifier.size(8.dp))
                Img(modifier = Modifier.size(y.dp, x.dp), imageUrls[1])
            }
        }

        else -> {
            Row {
                Spacer(modifier = Modifier.size(16.dp))
                Img(modifier = Modifier.size(x.dp, x.dp), imageUrls[0])
                Spacer(modifier = Modifier.size(8.dp))
                Column {
                    Img(modifier = Modifier.size(y.dp, y.dp), imageUrls[1])
                    Spacer(modifier = Modifier.size(8.dp))
                    Img(modifier = Modifier.size(y.dp, y.dp), imageUrls[2])
                }
            }
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