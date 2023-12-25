package com.example.haminjast.ui.screen.myPoster

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.example.haminjast.ui.screen.meScreen.MeViewModel
import com.example.haminjast.ui.screen.meScreen.provideUserRepository
import com.example.haminjast.ui.theme.PrimaryBlack
import com.example.haminjast.ui.theme.VazirFont

@Composable
fun MyPosterScreen(
    loginDataStore: LoginDataStore,
    meViewModel: MeViewModel = viewModel(
        factory = com.example.haminjast.ui.screen.meScreen.provideViewModelFactory(
            loginDataStore = loginDataStore,
            userRepository = provideUserRepository()
        )
    ),
    onPosterClicked : (Int)-> Unit = {}
){
    val posters = meViewModel.posters.collectAsState()
    LazyColumn(modifier = Modifier.fillMaxSize()){
        item {
            MyPosterHeader(posters.value.size)
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp),
                color = PrimaryBlack.copy(alpha = 0.1f)
            )
        }
        items(posters.value.size){
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

@Composable
fun MyPosterHeader(count : Int) {
    Text(text = "تعداد آگهی های من : $count",
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
