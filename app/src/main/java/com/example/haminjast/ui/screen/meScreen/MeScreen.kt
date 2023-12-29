package com.example.haminjast.ui.screen.meScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.haminjast.R
import com.example.haminjast.data.datastore.LoginDataStore
import com.example.haminjast.ui.screen.ads.PosterItem
import com.example.haminjast.ui.theme.PrimaryBlack
import com.example.haminjast.ui.theme.VazirFont
import eu.bambooapps.material3.pullrefresh.PullRefreshIndicator
import eu.bambooapps.material3.pullrefresh.rememberPullRefreshState

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MeScreen(
    loginDataStore: LoginDataStore,
    meViewModel: MeViewModel = viewModel(
        factory = provideViewModelFactory(
            loginDataStore = loginDataStore,
            userRepository = provideUserRepository()
        )
    ),
    onPosterClicked: (Int) -> Unit = {}
) {
    val markedPoster = meViewModel.markedPosters.collectAsState()
    val wallet = meViewModel.wallet.collectAsState()
    val posters = meViewModel.posters.collectAsState()
    var tabIndex by remember { mutableIntStateOf(0) }
    val isLogin = remember {
        mutableStateOf(loginDataStore.readTokenF())
    }
    if (isLogin.value.isEmpty()) {
        EmptyState()
    } else {
        Column (
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ){
            Wallet(wallet.value)
            val tabs = listOf("آگهی های من", "آگهی های نشان شده")
            Column(modifier = Modifier.fillMaxSize()) {
                TabRow(selectedTabIndex = tabIndex , containerColor = Color(0xFFFFFFFF)) {
                    tabs.forEachIndexed { index, title ->
                        Tab(text = { Text(title) },
                            modifier = if(tabIndex == index) Modifier.padding(horizontal = 30.dp, vertical = 5.dp).background(color = Color(0xFFEFF8FF), shape = RoundedCornerShape(size = 8.dp))
                            else Modifier.padding(horizontal = 30.dp, vertical = 5.dp).background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(size = 8.dp)),
                            selected = tabIndex == index,
                            onClick = { tabIndex = index },
                            selectedContentColor = Color(0xFF3A3A3A),
                            unselectedContentColor = Color(0xCC3A3A3A)
                        )
                    }
                }
                when (tabIndex) {
                    0 -> {
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            item {
                                MyPosterSaveHeader(posters.value.size) { meViewModel.refresh() }
                            }
                            items(posters.value.size) {
                            PosterItem(
                                posters.value[it],
                                onPosterClicked = onPosterClicked,
                                )
                            Divider(
                            modifier = Modifier
                            .fillMaxWidth()
                            .height(2.dp),
                            color = PrimaryBlack.copy(alpha = 0.1f)
                            )
                            }
                        }
                    }
                    1 -> {
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            item {
                                MyPosterSaveHeader(markedPoster.value.size) { meViewModel.refresh() }
                            }
                            items(markedPoster.value.size) {
                                PosterItem(
                                    markedPoster.value[it],
                                    onPosterClicked = onPosterClicked,
                                )
                                Divider(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(2.dp),
                                    color = PrimaryBlack.copy(alpha = 0.1f)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun Wallet(wallet : Int) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = "موجودی شما :",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.vazir)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF3A3A3A),
                    textAlign = TextAlign.Right,
                )
            )
            Spacer(modifier = Modifier.padding(5.dp))
            Text(
                text = "$wallet تومان",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.vazir)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF3A3A3A),
                    textAlign = TextAlign.Right,
                )
            )
        }
        Button(onClick = { /*TODO*/ },Modifier,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF687EFF))
        ) {
            Text(
                text = "افزایش موجودی",
                style = TextStyle(
                    fontSize = 12.sp,
                    fontFamily = FontFamily(Font(R.font.vazir)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFFFFFFFF),
                    textAlign = TextAlign.Right,
                )
            )
        }
    }
}

@Composable
fun EmptyState() {
    Text(
        text = "برای دیدن آگهی های نشان شده و موجودی حساب باید وارد شوید",
        style = TextStyle(
            fontSize = 20.sp,
            fontFamily = VazirFont,
            fontWeight = FontWeight(400),
            color = PrimaryBlack.copy(alpha = 0.8f),
            textAlign = TextAlign.Right,
        ),
        modifier = Modifier.padding(20.dp)
    )
}

@Composable
fun MyPosterSaveHeader(count: Int ,onClick : () -> Unit ={}) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = "تعداد : $count",
            style = TextStyle(
                fontSize = 15.sp,
                fontFamily = VazirFont,
                fontWeight = FontWeight(400),
                color = PrimaryBlack.copy(alpha = 0.8f),
                textAlign = TextAlign.Right,
            ),
            modifier = Modifier.padding(5.dp)
        )
        IconButton(onClick = onClick , modifier = Modifier
            .size(25.dp)
            .padding(5.dp)) {
            Icon(painter = painterResource(id = R.drawable.ic_retry), contentDescription = null)
        }
    }

}

