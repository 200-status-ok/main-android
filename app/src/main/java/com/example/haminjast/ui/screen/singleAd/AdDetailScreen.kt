@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.haminjast.ui.screen.singleAd

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.haminjast.R
import com.example.haminjast.ui.screen.singleAd.component.ContactsBottomSheetContent
import com.example.haminjast.ui.theme.Gray
import com.example.haminjast.ui.theme.PrimaryBlack
import com.example.haminjast.ui.theme.PrimaryBlue
import com.example.haminjast.ui.theme.VazirFont
import com.example.haminjast.ui.util.RTLPixel5Previews
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdDetailScreen() {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    val corScope = rememberCoroutineScope()

    BottomSheetScaffold(
        topBar = {
            SingleAdTopBar()
        },
        sheetContent = {
            ContactsBottomSheetContent(onToggleBottomSheet = {
                corScope.launch {
                    if (bottomSheetScaffoldState.bottomSheetState.currentValue==SheetValue.PartiallyExpanded) {
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
        SingleAdContent(innerPadding)
    }
}


@Composable
@RTLPixel5Previews
fun SingleAdScreenPreview() {
    AdDetailScreen()
}


@Composable
fun SingleAdTopBar() {
    TopAppBar(title = { /*TODO*/ }, navigationIcon = {
        IconButton(onClick = { }) {
            Image(painter = painterResource(id = R.drawable.ic_back), contentDescription = null)
        }
    }, actions = {
        IconButton(onClick = { }) {
            Image(painter = painterResource(id = R.drawable.ic_bookmark), contentDescription = null)
        }
        IconButton(onClick = { }) {
            Image(
                painter = painterResource(id = R.drawable.ic_3_dots_menu),
                contentDescription = null
            )
        }
    },
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White),
        )
}

@Composable
@Preview(locale = "fa")
fun SingleAdTopBarPreview() {
    SingleAdTopBar()
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SingleAdContent(innerPadding: PaddingValues) {
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
            AdHeader()
            Spacer(modifier = Modifier.size(16.dp))
            RewardBar()
            Spacer(modifier = Modifier.size(16.dp))
            TextTitleDescription()
            Spacer(modifier = Modifier.size(16.dp))
            TextTitleDescription()
        }
    }
}

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

@Composable
fun AdHeader() {
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
fun AdHeaderPreview() {
    AdHeader()
}

@Composable
fun RewardBar() {
    Surface(modifier = Modifier.fillMaxWidth(), color = PrimaryBlue) {
        Row(
            modifier = Modifier
                .padding(start = 20.dp, end = 30.dp)
                .height(40.dp), horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "مژدگانی",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = VazirFont,
                    fontWeight = FontWeight(400),
                    color = PrimaryBlack.copy(alpha = 0.9f),
                    textAlign = TextAlign.Right,
                )
            )
            Text(
                text = "۱۰۰،۰۰۰ تومان",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = VazirFont,
                    fontWeight = FontWeight(400),
                    color = PrimaryBlack.copy(alpha = 0.9f),
                    textAlign = TextAlign.Right,
                )
            )
        }
    }
}

@RTLPixel5Previews
@Composable
fun RewardBarPreview() {
    RewardBar()
}

@Composable
fun TextTitleDescription() {
    Column(modifier = Modifier.padding(start = 20.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "توضیحات",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = VazirFont,
                    fontWeight = FontWeight(500),
                    color = PrimaryBlack.copy(alpha = 0.9f),
                    textAlign = TextAlign.Right,
                )
            )
            Spacer(modifier = Modifier.width(24.dp))
            Text(
                text = ".......................................",
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = VazirFont,
                    fontWeight = FontWeight(600),
                    color = PrimaryBlack.copy(alpha = 0.4f),
                    textAlign = TextAlign.Right,
                    letterSpacing = 2.8.sp,
                ),
                maxLines = 1
            )
        }
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            modifier = Modifier.padding(end = 20.dp),
            text = "یکی از مشکلاتی که مشکلان شصیشسطز لفذذر زی بیشتر افراد با آن مواجه هستند. یکی از مشکلاتی که بیشتر افراد با آن مواجه هستند.یکی از مشکلاتی که مشکلان شصیشسطز لفذذر زی بیشتر افراد با آن مواجه هستند. یکی از مشکلاتی که بیشتر افراد با آن مواجه هستند.یکی از مشکلاتی که مشکلان شصیشسطز لفذذر زی بیشتر افراد با آن مواجه هستند.\n یکی از مشکلاتی که بیشتر افراد با آن مواجه هستند.یکی از مشکلاتی که مشکلان شصیشسطز لفذذر زی بیشتر افراد با آن مواجه هستند. یکی از مشکلاتی که بیشتر افراد با آن مواجه هستند.",
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

@RTLPixel5Previews
@Composable
fun TextTitleDescriptionPreview() {
    TextTitleDescription()
}
