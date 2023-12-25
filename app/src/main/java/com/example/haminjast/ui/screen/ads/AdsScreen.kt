package com.example.haminjast.ui.screen.ads

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.haminjast.R
import com.example.haminjast.data.model.posterToUiPoster
import com.example.haminjast.ui.model.Contact
import com.example.haminjast.ui.model.PosterStatus
import com.example.haminjast.ui.model.UiPoster
import com.example.haminjast.ui.theme.PrimaryBlack
import com.example.haminjast.ui.theme.PrimaryBlue
import com.example.haminjast.ui.theme.PrimaryYellow
import com.example.haminjast.ui.theme.VazirFont
import com.example.haminjast.ui.util.RTLPixel5Previews
import com.utsman.osmandcompose.Marker
import com.utsman.osmandcompose.OpenStreetMap
import com.utsman.osmandcompose.rememberCameraState
import com.utsman.osmandcompose.rememberMarkerState
import eu.bambooapps.material3.pullrefresh.PullRefreshIndicator
import eu.bambooapps.material3.pullrefresh.pullRefresh
import eu.bambooapps.material3.pullrefresh.rememberPullRefreshState
import org.osmdroid.util.GeoPoint

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdsScreen(
    viewModel: AdsViewModel = viewModel(
        factory = provideViewModelFactory(
            context = LocalContext.current
        )
    ),
    onPosterClicked: (Int) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val isShowingMap = viewModel.isShowingMap.collectAsStateWithLifecycle()
        val posters = viewModel.posters.collectAsLazyPagingItems()
        val screenState by viewModel.screenState.collectAsStateWithLifecycle()

        Log.d("modar", "${posters.itemSnapshotList.items}");

        val cameraState = rememberCameraState {
            geoPoint = GeoPoint(35.7219, 51.3347)
            zoom = 12.0
        }
        val depokMarkerState = rememberMarkerState(
            geoPoint = GeoPoint(35.7219, 51.3347)
        )

        SearchAndFilterLayout(
            modifier = Modifier
                .fillMaxWidth()
                .background(PrimaryYellow)
                .animateContentSize(),
            posterFilterOptions = screenState.posterFilterOptions,
            text = screenState.searchPhrase,
            isExpanded = screenState.isFilterOptionsExpanded,
            onPosterTypeItemSelected = { index ->
                viewModel.updateScreenState {
                    it.copy(
                        posterFilterOptions = it.posterFilterOptions.copy(
                            posterType = PosterFilterOptions.PosterTypeOptions.values()[index]
                        )
                    )
                }
            },
            onSelectLocationOnMapClicked = {
                //TODO
            },
            onSelectStartDateClicked = {
                //TODO
            },
            onSelectEndDateClicked = {
                //TODO
            },
            onOnlySpecialPosterToggle = {
                viewModel.updateScreenState {
                    it.copy(
                        posterFilterOptions = it.posterFilterOptions.copy(
                            onlySpecialPosters = !it.posterFilterOptions.onlySpecialPosters
                        )
                    )
                }
            },
            onSearchClicked = {
//                viewModel.retry()
//                viewModel.updateScreenState { it.copy(isFilterOptionsExpanded = false) }
            },
            onFilterClicked = {
                viewModel.updateScreenState { it.copy(isFilterOptionsExpanded = true) }
            },
            onCloseFilterOptionsClicked = {
                viewModel.updateScreenState { it.copy(isFilterOptionsExpanded = false) }
            },
            onResetFilterOptionsClicked = {
                viewModel.updateScreenState {
                    it.copy(
                        posterFilterOptions = PosterFilterOptions(),
                    )
                }
            },
            onValueChange = { value ->
                viewModel.updateScreenState {
                    it.copy(searchPhrase = value)
                }
            }
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RectangleShape)
        ) {
            if (isShowingMap.value) {
                OpenStreetMap(
                    modifier = Modifier.fillMaxSize(),
                    cameraState = cameraState,
                    onMapLongClick = {
                        depokMarkerState.geoPoint = it
                    },
                    depokMarkerState = depokMarkerState
                ) {
                    Marker(
                        state = depokMarkerState,
                        icon = LocalContext.current.getDrawable(R.drawable.ic_location)
                    )
                }

            } else {

                val isRefreshing by remember { // TODO this has not been handled properly
                    mutableStateOf(false)
                }
                val state = rememberPullRefreshState(
                    refreshing = isRefreshing,
                    onRefresh = { posters.refresh() })

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .pullRefresh(state)
                ) {
                    items(posters.itemCount) {
                        posters[it]?.let { poster ->
                            PosterItem(posterToUiPoster(poster), onPosterClicked)
                            Divider(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(2.dp),
                                color = PrimaryBlack.copy(alpha = 0.1f)
                            )
                        }
                    }
                    posters.apply {
                        when {
                            loadState.refresh is LoadState.Loading -> {
                                item { PageLoader(modifier = Modifier.fillParentMaxSize()) }
                            }

                            loadState.refresh is LoadState.Error -> {
                                val error = posters.loadState.refresh as LoadState.Error
                                item {
                                    ErrorMessage(
                                        modifier = Modifier.fillParentMaxSize(),
                                        message = error.error.localizedMessage!!,
                                        onClickRetry = { retry() })
                                }
                            }

                            loadState.append is LoadState.Error -> {
                                val error = posters.loadState.append as LoadState.Error
                                item {
                                    ErrorMessage(
                                        modifier = Modifier,
                                        message = error.error.localizedMessage!!,
                                        onClickRetry = { retry() })
                                }
                            }
                        }
                    }
                }

                PullRefreshIndicator(
                    refreshing = isRefreshing, state = state,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                )
            }
        }
    }
}

@Composable
fun PageLoader(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "در حال دریافت آگهی ها",
            color = MaterialTheme.colorScheme.primary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        CircularProgressIndicator(Modifier.padding(top = 10.dp))
    }
}


@Composable
fun ErrorMessage(
    message: String,
    modifier: Modifier = Modifier,
    onClickRetry: () -> Unit
) {
    Row(
        modifier = modifier.padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = message,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.weight(1f),
            maxLines = 2
        )
        OutlinedButton(onClick = onClickRetry) {
            Text(text = "retry")
        }
    }
}

@Composable
fun PosterItem(ad: UiPoster, onPosterClicked: (Int) -> Unit = {}) {
    Column(modifier = Modifier.clickable {
        onPosterClicked(ad.id)
    }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(118.dp)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(86.dp)
                    .clip(RoundedCornerShape(4.dp)),
                model = if (!ad.imageUrls.isNullOrEmpty()) ad.imageUrls[0] else null,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .padding(top = 4.dp, bottom = 4.dp, start = 12.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = ad.title,
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = VazirFont,
                            fontWeight = FontWeight(600),
                            color = PrimaryBlack,
                            textAlign = TextAlign.Right,
                        )
                    )
                    Text(
                        text = ad.description,
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = VazirFont,
                            fontWeight = FontWeight(400),
                            color = PrimaryBlack.copy(alpha = 0.65f),
                            textAlign = TextAlign.Right,
                            lineHeight = 16.sp
                        ),
                        maxLines = 2
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = ad.timeCreated,
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = VazirFont,
                            fontWeight = FontWeight(400),
                            color = PrimaryBlack,
                            textAlign = TextAlign.Right,
                        )
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Image(
                        modifier = Modifier
                            .size(6.dp)
                            .padding(top = 3.dp),
                        painter = painterResource(id = R.drawable.dot),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = "${stringResource(id = ad.status.value)} در ${ad.vicinity}",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = VazirFont,
                            fontWeight = FontWeight(400),
                            color = PrimaryBlack,
                            textAlign = TextAlign.Right,
                        )
                    )
                }
            }
        }
    }
}

@RTLPixel5Previews
@Composable
fun PosterItemPreview() {
    PosterItem(
        ad = UiPoster(
            id = 1,
            title = "گربه گم شده",
            description = "گربه گم شده در میدان ونک پیش گم شده است. لطفا اگر پیدا کردید با شماره زیر تماس بگیرید.",
            imageUrls = listOf("https://picsum.photos/200/300"),
            timeCreatedTimeStamp = 0,
            status = PosterStatus.Lost,
            vicinity = "میدان ونک",
            reward = 100000,
            lat = 35.7219,
            lng = 51.3347,
            issuerId = 1,
            contacts = listOf(
                Contact(
                    name = "تلفن تماس",
                    value = "09123456789"
                ),
                Contact(
                    name = "تلگرام",
                    value = "t.me/haminjast"
                )
            )
        )
    )
}