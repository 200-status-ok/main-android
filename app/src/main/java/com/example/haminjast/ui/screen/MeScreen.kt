package com.example.haminjast.ui.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.haminjast.data.datastore.LoginDataStore
import com.example.haminjast.ui.screen.ads.PosterItem
import com.example.haminjast.ui.screen.myPoster.MyPosterViewModel
import com.example.haminjast.ui.screen.myPoster.provideViewModelFactory
import com.example.haminjast.ui.theme.PrimaryBlack
import com.example.haminjast.ui.theme.VazirFont

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MeScreen(
    viewModel: MyPosterViewModel = viewModel(
        factory = provideViewModelFactory(
            context = LocalContext.current
        )
    ),
    loginDataStore: LoginDataStore,
    onPosterClicked: (Int) -> Unit = {}
) {
    val isLogin = remember {
        mutableStateOf(loginDataStore.readTokenF())
    }
    if (isLogin.value.isEmpty()) {
        EmptyState()
    } else {
        Column (
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {
            Wallet(loginDataStore)

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp),
                color = PrimaryBlack.copy(alpha = 0.1f)
            )
            Text(
                text = " آگهی های نشان شده :",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = VazirFont,
                    fontWeight = FontWeight(400),
                    color = PrimaryBlack.copy(alpha = 0.8f),
                    textAlign = TextAlign.Right,
                ),
                modifier = Modifier.padding(20.dp)
            )
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp),
                color = PrimaryBlack.copy(alpha = 0.1f)
            )
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    MyPosterSaveHeader(5)
                }
                items(viewModel.myPosters.size) {
                    PosterItem(
                        viewModel.myPosters[it],
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

@Composable
private fun Wallet(loginDataStore: LoginDataStore) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "موجودی حساب : ${loginDataStore.readWallet()} تومان",
            style = TextStyle(
                fontSize = 15.sp,
                fontFamily = VazirFont,
                fontWeight = FontWeight(400),
                color = PrimaryBlack.copy(alpha = 0.8f),
                textAlign = TextAlign.Right,
            ),
            modifier = Modifier.padding(horizontal = 10.dp)
        )
        OutlinedButton(onClick = { /*TODO*/ }) {
            Text(
                text = "افزایش موجودی",
                style = TextStyle(
                    fontSize = 10.sp,
                    fontFamily = VazirFont,
                    fontWeight = FontWeight(400),
                    color = PrimaryBlack.copy(alpha = 0.8f),
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
fun MyPosterSaveHeader(count: Int) {
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
}

