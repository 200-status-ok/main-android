package com.example.haminjast.ui.screen.ads

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.haminjast.data.model.Ad
import com.example.haminjast.data.model.fakeAdList
import com.example.haminjast.ui.theme.PrimaryBlack

@Composable
fun AdsScreen(
    viewModel: AdsViewModel = viewModel(
        factory = provideViewModelFactory(
            context = LocalContext.current
        )
    ),
    onPosterClicked: (Int) -> Unit = {}
) {
    val posters = viewModel.posters.collectAsLazyPagingItems()
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(fakeAdList) {
                PosterItem(ad = it, onPosterClicked = onPosterClicked)
            }
        }
    }
}

@Composable
fun PosterItem(ad: Ad, onPosterClicked: (Int) -> Unit = {}) {
    Column(modifier = Modifier.clickable {
        onPosterClicked(0) //todo set poster id
    }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(8.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(104.dp)
                    .clip(RoundedCornerShape(2.dp)),
                model = ad.imgUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(text = ad.title)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = ad.desc, fontSize = 12.sp)
                }
                Text(
                    modifier = Modifier,
                    text = "${ad.date} | ${stringResource(id = ad.status.stringRes)} در ${ad.location}",
                    fontSize = 12.sp
                )
            }
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp),
            color = PrimaryBlack.copy(alpha = 0.1f)
        )
    }
}

@Preview
@Composable
fun AdItemP() {
    PosterItem(fakeAdList[0])
}