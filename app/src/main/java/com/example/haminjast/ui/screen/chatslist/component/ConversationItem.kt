package com.example.haminjast.ui.screen.chatslist.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.haminjast.R
import com.example.haminjast.ui.model.UiConversation
import com.example.haminjast.ui.screen.common.TagText
import com.example.haminjast.ui.theme.PrimaryBlack
import com.example.haminjast.ui.theme.PrimaryBlue
import com.example.haminjast.ui.theme.TagGray
import com.example.haminjast.ui.theme.VazirFont
import com.example.haminjast.ui.util.RTLPixel5Previews

@Composable
fun ConversationItem(conversation: UiConversation, onClick: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .clickable { onClick() }
            .padding(horizontal = 16.dp), verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(4.dp)),
                model = conversation.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(start = 12.dp, top = 2.dp, bottom = 2.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        modifier = Modifier.size(4.dp),
                        painter = painterResource(id = R.drawable.dot),
                        contentDescription = null
                    )
                    Text(
                        modifier = Modifier.padding(start = 6.dp),
                        text = conversation.title,
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = VazirFont,
                            fontWeight = FontWeight(600),
                            color = PrimaryBlack,
                            textAlign = TextAlign.Right,
                        )
                    )
                    Spacer(modifier = Modifier.weight(1f))

                    if (conversation.myPoster) {
                        TagText(
                            text = stringResource(id = R.string.my_poster),
                            backgroundColor = TagGray
                        )
                        Icon(
                            modifier = Modifier
                                .padding(horizontal = 6.dp)
                                .size(3.dp),
                            painter = painterResource(id = R.drawable.dot),
                            contentDescription = null,
                            tint = PrimaryBlack
                        )
                    }

                    Text(
                        modifier = Modifier,
                        text = conversation.lastMessageDate,
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontFamily = VazirFont,
                            fontWeight = FontWeight(400),
                            color = PrimaryBlack,
                            textAlign = TextAlign.Right,
                        )
                    )
                }

                if (conversation.unreadCount > 0) {
                    TagText(
                        text = "${conversation.unreadCount} ${stringResource(id = R.string.description)}",
                        backgroundColor = PrimaryBlue
                    )
                }

            }
        }

        Text(
            modifier = Modifier.padding(top = 8.dp),
            text = conversation.lastMessage,
            style = TextStyle(
                fontSize = 12.sp,
                fontFamily = VazirFont,
                fontWeight = FontWeight(400),
                color = PrimaryBlack.copy(alpha = 0.8f)
            ),
            textAlign = TextAlign.Right,
        )
    }
}

@RTLPixel5Previews
@Composable
fun ConversationItemPreview() {
    ConversationItem(
        UiConversation(
            id = 0,
            title = "دوچرخه",
            imageUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/3/3c/WalletMpegMan.jpg/500px-WalletMpegMan.jpg",
            lastMessage = "سلام. دوچرخه رو پیدا کردم. اگه میخوای بیا بگیرش",
            lastMessageDate = "۳ دقیقه پیش",
            unreadCount = 2,
            myPoster = true,
        )
    )
}