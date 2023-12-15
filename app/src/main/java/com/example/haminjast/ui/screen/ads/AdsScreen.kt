package com.example.haminjast.ui.screen.ads

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.haminjast.R
import com.example.haminjast.data.datastore.LoginDataStore
import com.example.haminjast.data.model.posterToUiPoster
import com.example.haminjast.ui.component.SelectionType
import com.example.haminjast.ui.component.ToggleButton
import com.example.haminjast.ui.component.ToggleButtonOption
import com.example.haminjast.ui.model.UiPoster
import com.example.haminjast.ui.screen.createPoster.component.LostOrFoundToggle
import com.example.haminjast.ui.theme.PrimaryBlack
import com.example.haminjast.ui.theme.VazirFont
import okhttp3.internal.notify
import okhttp3.internal.notifyAll

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
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val posters = viewModel.posters.collectAsLazyPagingItems()
        val searchQuery = viewModel.posterSearchQuery.collectAsState()
        var isExpanded by remember { mutableStateOf(false) }
        val posterStatus = viewModel.posterStatus.collectAsState()
        val onlyWithAward = viewModel.onlyWithAward.collectAsState()
        val posterState = viewModel.posterState.collectAsState()
        val posterSpecialType = viewModel.posterSpecialType.collectAsState()
        val posterSortBy = viewModel.posterSortBy.collectAsState()
        val posterSortOrder = viewModel.posterSortOrder.collectAsState()

        SearchBar(
            status = posterStatus.value,
            sort = posterSortOrder.value,
            sortBy = posterSortBy.value,
            state = posterState.value,
            specialType = posterSpecialType.value,
            onlyWithAward = onlyWithAward.value,
            text = searchQuery.value,
            isExpanded = isExpanded,
            onIsExpandedChange = {
                isExpanded = it
            },
            onValueChange = {
                viewModel.onPosterSearchQueryChanged(it)
                viewModel.retry()
            },
            onSearchClick = {
                viewModel.retry()
                posters.refresh()
            },
            onClickStatus = {
                if (it[0].text == "همه") {
                    viewModel.onPosterStatusChanged("both")
                } else if (it[0].text == "گم شده") {
                    viewModel.onPosterStatusChanged("lost")
                } else if (it[0].text == "پیدا شده") {
                    viewModel.onPosterStatusChanged("found")
                }
            },
            onClickSortBy = {
                if (it[0].text == "تاریخ ساخت آگهی") {
                    viewModel.onPosterSortByChanged("created_at")
                } else if (it[0].text == "تاریخ آپدبت آگهی") {
                    viewModel.onPosterSortByChanged("updated_at")
                }
            },
            onClickSortOrder = {
                if (it[0].text == "نزولی") {
                    viewModel.onPosterSortOrderChanged("desc")
                } else if (it[0].text == "صعودی") {
                    viewModel.onPosterSortOrderChanged("asc")
                }
            },
            onClickState = {
                if (it[0].text == "همه") {
                    viewModel.onPosterStateChanged("all")
                } else if (it[0].text == "در حال بررسی") {
                    viewModel.onPosterStateChanged("pending")
                } else if (it[0].text == "تایید شده") {
                    viewModel.onPosterStateChanged("approved")
                } else if (it[0].text == "رد شده") {
                    viewModel.onPosterStateChanged("rejected")
                }
            },
            onClickSpecialType = {
                if (it[0].text == "همه") {
                    viewModel.onPosterSpecialTypeChanged("all")
                } else if (it[0].text == "عادی") {
                    viewModel.onPosterSpecialTypeChanged("normal")
                } else if (it[0].text == "ویژه") {
                    viewModel.onPosterSpecialTypeChanged("special")
                }
            },
            onClickAward = {
                viewModel.onOnlyWithAwardChanged(it)
            },
        )
        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
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
                .height(112.dp)
                .padding(8.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(96.dp)
                    .clip(RoundedCornerShape(4.dp)),
//                model = ad.imageUrls?.get(0),
                model = null ,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = ad.title,
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = VazirFont,
                            fontWeight = FontWeight(600),
                            color = PrimaryBlack.copy(alpha = 0.9f),
                            textAlign = TextAlign.Right,
                        )
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = ad.description,
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = VazirFont,
                            fontWeight = FontWeight(400),
                            color = PrimaryBlack.copy(alpha = 0.8f)
                        ),
                        textAlign = TextAlign.Right,
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
                            color = PrimaryBlack.copy(alpha = 0.8f),
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
                            color = PrimaryBlack.copy(alpha = 0.8f),
                            textAlign = TextAlign.Right,
                        )
                    )
                }
            }
        }
    }
}