@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.haminjast.ui.screen.posterDetail

import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.haminjast.ui.screen.posterDetail.component.ContactsBottomSheetContent
import com.example.haminjast.ui.screen.posterDetail.component.PosterDetailContent
import com.example.haminjast.ui.screen.posterDetail.component.PosterDetailTopBar
import com.example.haminjast.ui.theme.Gray
import com.example.haminjast.ui.util.RTLPixel5Previews
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PosterDetailScreen(
    posterId: Int = 0,
    viewModel: PosterDetailViewModel = viewModel(
        factory = PosterDetailViewModelFactory(
            posterId
        )
    )
) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    BottomSheetScaffold(
        topBar = {
            PosterDetailTopBar()
        },
        sheetContent = {
            ContactsBottomSheetContent(onToggleBottomSheet = {
                coroutineScope.launch {
                    if (bottomSheetScaffoldState.bottomSheetState.currentValue == SheetValue.PartiallyExpanded) {
                        bottomSheetScaffoldState.bottomSheetState.expand()
                    } else {
                        bottomSheetScaffoldState.bottomSheetState.partialExpand()
                    }
                }
            })
        },
        scaffoldState = bottomSheetScaffoldState,
        sheetPeekHeight = 61.5.dp,
        sheetDragHandle = null,
        sheetSwipeEnabled = false,
        sheetShape = RectangleShape,
        sheetContainerColor = Gray,
        containerColor = Color.White
    ) { innerPadding ->
        PosterDetailContent(innerPadding)
    }
}

@Composable
@RTLPixel5Previews
fun PosterDetailScreenPreview() {
    PosterDetailScreen()
}


