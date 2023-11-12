package com.example.haminjast.ui.screen.chatslist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.haminjast.ui.model.UiConversation
import com.example.haminjast.ui.screen.chatslist.component.ConversationItem
import com.example.haminjast.ui.theme.DividerGray
import com.example.haminjast.ui.util.RTLPixel5Previews
import kotlin.random.Random


val fakeConversationList = mutableListOf<UiConversation>().apply {
    for (i in 0..10) {
        add(
            UiConversation(
                id = i,
                title = "دوچرخه",
                imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3c/WalletMpegMan.jpg/500px-WalletMpegMan.jpg",
                lastMessage = "سلام. دوچرخه رو پیدا کردم. اگه میخوای بیا بگیرش",
                lastMessageDate = "۳ دقیقه پیش",
                unreadCount = Random(i).nextInt(0, 10),
                myPoster = Random(i).nextBoolean(),
            )
        )
    }
}

@Composable
fun ChatsListScreen(onChatClicked: () -> Unit = {}) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(fakeConversationList, key = { it.id }) {
            ConversationItem(it, onClick = onChatClicked)
            Divider(modifier = Modifier.fillMaxWidth(), thickness = 2.dp, color = DividerGray)
        }
    }
}

@RTLPixel5Previews
@Composable
fun ChatsListScreenPreview() {
    ChatsListScreen()
}