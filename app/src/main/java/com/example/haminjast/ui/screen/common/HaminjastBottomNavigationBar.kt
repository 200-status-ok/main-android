package com.example.haminjast.ui.screen.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.haminjast.R
import com.example.haminjast.ui.navigation.Ads
import com.example.haminjast.ui.navigation.ChatsList
import com.example.haminjast.ui.navigation.Me
import com.example.haminjast.ui.theme.NavBarBlue
import com.example.haminjast.ui.theme.PrimaryBlack
import com.example.haminjast.ui.theme.VazirFont
import com.example.haminjast.ui.util.RTLPixel5Previews

@Composable
fun HaminjastBottomNavigationBar(
    currentDestinationRoute: String?,
    onItemClicked: (String) -> Unit = {}
) {
    Surface(color = Color.White, shadowElevation = 8.dp, tonalElevation = 8.dp) {
        Row(
            modifier = Modifier
                .height(64.dp)
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
        ) {
            HaminjastBottomNavigationBarItem(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                label = stringResource(id = R.string.ads),
                icon = R.drawable.ic_haminjast,
                selected = currentDestinationRoute == Ads.route || currentDestinationRoute == null,
                onItemClicked = { onItemClicked(Ads.route) }
            )
            HaminjastBottomNavigationBarItem(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                label = stringResource(id = R.string.chat),
                icon = R.drawable.ic_chat_2,
                selected = currentDestinationRoute == ChatsList.route,
                onItemClicked = { onItemClicked(ChatsList.route) }
            )
            HaminjastBottomNavigationBarItem(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                label = stringResource(id = R.string.me),
                icon = R.drawable.ic_profile,
                selected = currentDestinationRoute == Me.route,
                onItemClicked = { onItemClicked(Me.route) }
            )
        }
    }
}

@Composable
fun HaminjastBottomNavigationBarItem(
    modifier: Modifier = Modifier,
    label: String,
    icon: Int,
    selected: Boolean,
    onItemClicked: () -> Unit
) {
    Button(
        modifier = modifier.padding(4.dp),
        onClick = { onItemClicked() },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = PrimaryBlack
        ),
        shape = RoundedCornerShape(32.dp),
        contentPadding = PaddingValues(0.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = if (selected) {
                    NavBarBlue
                } else {
                    PrimaryBlack.copy(alpha = 0.8f)
                }
            )
            Text(
                text = label,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = VazirFont,
                    fontWeight = FontWeight(400),
                    color = Color(0xCC3A3A3A),
                    textAlign = TextAlign.Right,
                ),
                color = if (selected) {
                    NavBarBlue
                } else {
                    PrimaryBlack.copy(alpha = 0.8f)
                }
            )
        }
    }
}

@RTLPixel5Previews
@Composable
fun HaminjastBottomNavigationBarPreview() {
    HaminjastBottomNavigationBar(currentDestinationRoute = null)
}

@RTLPixel5Previews
@Composable
fun HaminjastBottomNavigationBarItemPreview() {
    HaminjastBottomNavigationBarItem(
        label = stringResource(id = R.string.ads),
        icon = R.drawable.ic_haminjast,
        selected = true,
        onItemClicked = {}
    )
}
