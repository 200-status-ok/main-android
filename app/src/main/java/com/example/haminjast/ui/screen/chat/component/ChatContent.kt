package com.example.haminjast.ui.screen.chat.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.haminjast.R
import com.example.haminjast.ui.model.MessageStatus
import com.example.haminjast.ui.model.UiMessage
import com.example.haminjast.ui.theme.IncomingMessageColor
import com.example.haminjast.ui.theme.OutgoingMessageColor
import com.example.haminjast.ui.theme.PrimaryBlack
import com.example.haminjast.ui.theme.VazirFont
import com.example.haminjast.ui.util.RTLPixel5Previews


val fakeMessageList = mutableListOf<UiMessage>(
    UiMessage(1, "سلام. چطوری؟", MessageType.Incoming, "1400/01/01", "12:00"),
    UiMessage(
        2,
        "یکی از مشکلان شصیشسطز لفذذر زی بیشتر مشکلان شصیشسطز لفذذر یشصی صشش زی بیشتر مشکلاتی که مشکلز لفذذر زی بیشتر افراد با آن مواجه هستند.",
        MessageType.Incoming,
        "1400/01/01",
        "12:00"
    ),
    UiMessage(
        3,
        "یکی از مشکلان شصیشسطز لفذذر زی بیشتر مشکلان شصیشسطز لفذذر زی بیشتر مشکلاتی که مشکلز لفذذر زی بیشتر افراد با آن مواجه هستند.",
        MessageType.Outgoing,
        "1400/01/01",
        "12:00"
    ),
    UiMessage(
        4,
        "یکی از مشکلان شصیشسطز لفذذر زی بیشتر مشکلان شصیشسطز لفذذر یشصی صشش زی بیشتر مشکلاتی که مشکلز لفذذر زی بیشتر افراد با آن مواجه هستند.",
        MessageType.Incoming,
        "1400/01/01",
        "12:00"
    ),
    UiMessage(
        5,
        "یکی از مشکلان شصیشسطز لفذذر زیصشی بیشتر مشک زی بیشتر افراد با آن مواجه هستند.",
        MessageType.Outgoing,
        "1400/01/01",
        "12:00"
    ),
    UiMessage(
        6,
        "یکی از مشکلان شصیشسطز لفذذر زیصشی بیشتر مشک زی بیشتر افراد با آن مواجه هستند.",
        MessageType.Outgoing,
        "1400/01/01",
        "12:00"
    ),
    UiMessage(
        7,
        "یکی از مشکلان شصیشسطز لفذذر زیصشی بیشتر مشک زی بیشتر افراد با آن مواجه هستند.",
        MessageType.Outgoing,
        "1400/01/01",
        "12:00"
    ),
    UiMessage(
        8,
        "یکی از مشکلان شصیشسطز لفذذر زیصشی بیشتر مشک زی بیشتر افراد با آن مواجه هستند.",
        MessageType.Outgoing,
        "1400/01/01",
        "12:00"
    ),
    UiMessage(
        9,
        "یکی از مشکلان شصیشسطز لفذذر زیصشی بیشتر مشک زی بیشتر افراد با آن مواجه هستند.",
        MessageType.Outgoing,
        "1400/01/01",
        "12:00"
    ),
    UiMessage(
        10,
        "یکی از مشکلان شصیشسطز لفذذر زیصشی بیشتر مشک زی بیشتر افراد با آن مواجه هستند.",
        MessageType.Outgoing,
        "1400/01/01",
        "12:00"
    ),
    UiMessage(11, "یکی از مشکلان ", MessageType.Outgoing, "1400/01/01", "12:00"),
)

@Composable
fun ChatContent(
    outerPadding: PaddingValues = PaddingValues(),
    onBackClicked: () -> Unit = {},
    onMenuClicked: () -> Unit = {}
) {
    LazyColumn(
        modifier = Modifier
            .padding(outerPadding)
            .fillMaxSize(),
        reverseLayout = true
    ) {
        items(fakeMessageList.reversed(), key = { it.id }) {
            TextBubble(it)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun TextBubble(message: UiMessage) {
    CompositionLocalProvider(LocalLayoutDirection provides if (message.type == MessageType.Incoming) LayoutDirection.Ltr else LayoutDirection.Rtl) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        ) {
            Surface(
                modifier = Modifier
                    .heightIn(min = 32.dp)
                    .widthIn(max = 240.dp),
                shape = RoundedCornerShape(
                    topStart = 2.dp,
                    topEnd = 16.dp,
                    bottomEnd = 16.dp,
                    bottomStart = 16.dp
                ),
                color = message.type.containerColor,
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                    text = message.text,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = VazirFont,
                        fontWeight = FontWeight(400),
                        color = PrimaryBlack,
                        textAlign = TextAlign.Right,
                    )
                )
            }

            Row(
                modifier = Modifier
                    .align(Alignment.Bottom)
                    .padding(start = 8.dp, bottom = 2.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier,
                    text = "دیروز",
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontFamily = VazirFont,
                        fontWeight = FontWeight(400),
                        color = Color(0x993A3A3A),
                        textAlign = TextAlign.Right,
                    )
                )

                Image(
                    modifier = Modifier
                        .padding(start = 6.dp, end = 4.dp, top = 2.dp)
                        .size(3.dp),
                    painter = painterResource(id = R.drawable.dot),
                    contentDescription = null
                )

                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(id = when (message.status) {
                        MessageStatus.Pending -> R.drawable.ic_clock
                        MessageStatus.Sent -> R.drawable.ic_tick
                        MessageStatus.Seen -> R.drawable.ic_double_tick
                    }),
                    contentDescription = null,
                    tint = PrimaryBlack.copy(alpha = 0.6f)
                )
            }
        }
    }
}

@RTLPixel5Previews
@Composable
fun TextMessagePreview() {
    TextBubble(fakeMessageList[0])
}

@RTLPixel5Previews
@Composable
fun ChatContentPreview() {
    ChatContent()
}

enum class MessageType(
    val shape: RoundedCornerShape,
    val containerColor: Color,
    val horizontalArrangement: Arrangement.Horizontal
) {
    Incoming(
        RoundedCornerShape(
            topStart = 2.dp,
            topEnd = 16.dp,
            bottomEnd = 16.dp,
            bottomStart = 16.dp
        ), IncomingMessageColor, Arrangement.End
    ),
    Outgoing(
        RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 2.dp,
            bottomEnd = 16.dp,
            bottomStart = 16.dp
        ), OutgoingMessageColor, Arrangement.Start
    )
}
