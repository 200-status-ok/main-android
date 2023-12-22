package com.example.haminjast.ui.screen.createPoster.component

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.haminjast.R
import com.example.haminjast.ui.screen.createPoster.UploadStatus
import com.example.haminjast.ui.screen.createPoster.UploadedImage
import com.example.haminjast.ui.theme.PrimaryBlack
import com.example.haminjast.ui.theme.VazirFont
import com.example.haminjast.ui.util.RTLPixel5Previews

@Composable
fun ImageSelector(
    uploadedImages: List<UploadedImage> = listOf(),
    onSelectorClicked: () -> Unit = {},
    onRetryUploadClicked: (Uri) -> Unit = {},
    screenWidth: Int
) {
    val biggerSide = (screenWidth - 32 - 8) * 0.7f
    val smallerSide = (screenWidth - 32 - 8) - biggerSide

    val x = screenWidth - 32


    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(x.dp * 0.6f),
        contentPadding = PaddingValues(horizontal = 20.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Selector(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(if (uploadedImages.isEmpty()) x.dp else x.dp * 0.6f),
                onSelectorClicked = onSelectorClicked
            )
        }
        items(uploadedImages, key = { it.uri }) { uploadedImage ->
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .widthIn(min = 60.dp)
                    .clip(RoundedCornerShape(6.dp))
            ) {
                AsyncImage(
                    model = uploadedImage.uri,
                    contentDescription = null
                )
                Surface(
                    modifier = Modifier.matchParentSize(),
                    color = Color.White.copy(alpha = if (uploadedImage.uploadStatus != UploadStatus.UploadSucceed) 0.45f else 0f)
                ) {}
                if (uploadedImage.uploadStatus == UploadStatus.Uploading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(32.dp)
                            .align(Alignment.Center),
                        color = Color.White
                    )
                } else if (uploadedImage.uploadStatus == UploadStatus.UploadFailed) {
                    IconButton(
                        modifier = Modifier
                            .size(48.dp)
                            .align(Alignment.Center),
                        onClick = { onRetryUploadClicked(uploadedImage.uri) }) {
                        Icon(
                            modifier = Modifier.size(36.dp),
                            painter = painterResource(R.drawable.ic_retry),
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}

@RTLPixel5Previews
@Composable
fun ImageSelectorPreview() {
//    ImageSelector(
//        x = 222,
//        y = 107
//    )
}

@Composable
fun Selector(modifier: Modifier = Modifier, onSelectorClicked: () -> Unit = {}) {
    Column(
        modifier = modifier
//            .border(
//                width = 1.5.dp,
//                color = PrimaryBlack.copy(alpha = 0.4f),
//                shape = RoundedCornerShape(size = 6.dp)
//            )
            .background(
                color = PrimaryBlack.copy(alpha = 0.05f),
                shape = RoundedCornerShape(size = 6.dp)
            )
            .clip(RoundedCornerShape(size = 6.dp))
            .clickable {
                onSelectorClicked()
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(64.dp),
            painter = painterResource(id = R.drawable.ic_choose_image),
            contentDescription = null,
        )
        Spacer(modifier = Modifier.size(12.dp))
        Text(
            text = stringResource(R.string.add_picture),
            style = TextStyle(
                fontSize = 14.sp,
                fontFamily = VazirFont,
                fontWeight = FontWeight(600),
                color = PrimaryBlack.copy(alpha = 0.7f),
                textAlign = TextAlign.Right,
            )
        )
    }
}