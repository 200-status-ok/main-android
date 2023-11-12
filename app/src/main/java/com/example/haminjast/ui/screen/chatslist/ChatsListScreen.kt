package com.example.haminjast.ui.screen.chatslist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.haminjast.ui.screen.chatslist.component.ChatItem
import com.example.haminjast.ui.theme.DividerGray
import com.example.haminjast.ui.util.RTLPixel5Previews

@Composable
fun ChatsListScreen(onChatClicked: () -> Unit = {}) {
    LazyColumn(modifier = Modifier.fillMaxSize()){
        items(10){
            ChatItem(onClick = onChatClicked)
            Divider(modifier = Modifier.fillMaxWidth(), thickness = 2.dp, color = DividerGray)
        }
    }
}

@RTLPixel5Previews
@Composable
fun ChatsListScreenPreview() {
    ChatsListScreen()
}