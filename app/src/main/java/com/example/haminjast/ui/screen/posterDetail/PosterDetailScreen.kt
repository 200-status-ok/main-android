@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.haminjast.ui.screen.posterDetail

import android.util.Log
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.haminjast.ui.model.UiPoster
import com.example.haminjast.ui.screen.posterDetail.component.ContactsBottomSheetContent
import com.example.haminjast.ui.screen.posterDetail.component.PosterDetailContent
import com.example.haminjast.ui.screen.posterDetail.component.PosterDetailTopBar
import com.example.haminjast.ui.util.RTLPixel5Previews
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PosterDetailScreen(
    posterId: Int = 0,
    viewModel: PosterDetailViewModel = viewModel(
        factory = provideViewModelFactory(
            posterId,
        )
    ),
    onBackClicked: () -> Unit = {}
) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    val poster: State<UiPoster?> = viewModel.poster.collectAsStateWithLifecycle()
    Log.d("modar", "poster: ${poster.value}");

    val appBarState = rememberTopAppBarState()
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(appBarState)
    val isScrolled by produceState(
        initialValue = false,
        key1 = appBarState.contentOffset,
        producer = {
            value = appBarState.contentOffset < -1
        })

    Log.d("modar", "${appBarState.contentOffset}");

    BottomSheetScaffold(
        modifier = Modifier.nestedScroll(connection = scrollBehavior.nestedScrollConnection),
        topBar = {
            Surface(shadowElevation = if (isScrolled) 4.dp else 0.dp) {
                PosterDetailTopBar(
                    onBackClicked = onBackClicked,
                    scrollBehavior = scrollBehavior
                ) //todo: add bookmark and menu click
            }
        },
        sheetContent = {
            ContactsBottomSheetContent(
                contacts = poster.value?.contacts,
                onToggleBottomSheet = {
                    coroutineScope.launch {
                        if (bottomSheetScaffoldState.bottomSheetState.currentValue == SheetValue.PartiallyExpanded) {
                            bottomSheetScaffoldState.bottomSheetState.expand()
                        } else {
                            bottomSheetScaffoldState.bottomSheetState.partialExpand()
                        }
                    }
                }
            )
        },
        scaffoldState = bottomSheetScaffoldState,
        sheetPeekHeight = 61.5.dp,
        sheetDragHandle = null,
        sheetSwipeEnabled = false,
        sheetShape = RectangleShape,
        sheetShadowElevation = 8.dp,
        sheetTonalElevation = 8.dp,
        sheetContainerColor = Color.White,
        containerColor = Color.White
    ) { innerPadding ->
        PosterDetailContent(innerPadding, poster = poster.value)
    }
}

@Composable
@RTLPixel5Previews
fun PosterDetailScreenPreview() {
    PosterDetailScreen()
}


