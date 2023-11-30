package com.example.haminjast.ui.screen.chatslist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.haminjast.data.database.ApplicationDataBase
import com.example.haminjast.data.network.ChatService
import com.example.haminjast.data.network.posterretrofit.PosterRetrofit
import com.example.haminjast.data.network.posterretrofit.PosterRetrofitService
import com.example.haminjast.data.repository.ChatRepository
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
fun ChatsListScreen(
    onChatClicked: () -> Unit = {},
    viewModel: ChatListViewModel = viewModel(
        factory = ChatListViewModelFactory(
            chatRepository = ChatRepository.getInstance(
                ApplicationDataBase.getInstance(LocalContext.current).chatDao(),
                PosterRetrofit.getRetrofitInstance().create(ChatService::class.java)
            )
        )
    )
) {
    val conversationCovers by viewModel.conversationCovers.collectAsStateWithLifecycle()

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(conversationCovers, key = { it.id }) {
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