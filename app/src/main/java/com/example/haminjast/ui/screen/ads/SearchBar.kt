package com.example.haminjast.ui.screen.ads

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import com.example.haminjast.ui.theme.PrimaryBlack
import com.example.haminjast.ui.theme.VazirFont
import com.example.haminjast.ui.util.RTLPixel5Previews

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    searchPhrase: String = "",
    onSearchClicked: () -> Unit = {},
    onFilterClicked: () -> Unit = {},
    onSearchPhraseChanged: (String) -> Unit = {},
    hint: String = ""
) {
    Surface(modifier = modifier, shape = RoundedCornerShape(32.dp), color = Color.White) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .size(32.dp), onClick = onSearchClicked
            ) {
                Icon(
                    modifier = Modifier
                        .size(24.dp),
                    painter = painterResource(id = R.drawable.ic_search),
                    contentDescription = null
                )
            }

//            Text(
//                modifier = Modifier
//                    .padding(horizontal = 4.dp)
//                    .weight(1f),
//                text = searchPhrase,
//                style = TextStyle(
//                    fontSize = 14.sp,
//                    fontFamily = VazirFont,
//                    fontWeight = FontWeight(400),
//                    color = Color(0x663A3A3A),
//                    textAlign = TextAlign.Right,
//                )
//            )

//            BasicTextField(
//                modifier = Modifier
//                    .padding(horizontal = 4.dp)
//                    .weight(1f),
//                value = searchPhrase,
//                onValueChange = { onSearchPhraseChanged(it) },
//                textStyle = TextStyle(
//                    fontSize = 14.sp,
//                    fontFamily = VazirFont,
//                    fontWeight = FontWeight(400),
//                    color = PrimaryBlack.copy(alpha = 0.4f),
//                    textAlign = TextAlign.Right,
//                ),
//            )

            BasicTextField(
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .weight(1f),
                value = searchPhrase,
                singleLine = true,
                textStyle = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = VazirFont,
                    fontWeight = FontWeight(400),
                    color = PrimaryBlack,
                    textAlign = TextAlign.Right,
                ),
                onValueChange = { onSearchPhraseChanged(it) },
                decorationBox = { innerTextField ->
                    Box(
                        Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(),
                        contentAlignment = Alignment.CenterStart
                    ) {

                        if (searchPhrase.isEmpty()) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = hint,
                                style = TextStyle(
                                    fontSize = 14.sp,
                                    fontFamily = VazirFont,
                                    fontWeight = FontWeight(400),
                                    color = PrimaryBlack.copy(alpha = 0.4f),
                                    textAlign = TextAlign.Right,
                                )
                            )
                        }
                        // <-- Add this
                        innerTextField()
                    }
                },
            )

            IconButton(
                modifier = Modifier
                    .padding(end = 12.dp)
                    .size(32.dp), onClick = onFilterClicked
            ) {
                Icon(
                    modifier = Modifier
                        .size(24.dp),
                    painter = painterResource(id = R.drawable.ic_filter),
                    contentDescription = null
                )
            }
        }
    }
}


@RTLPixel5Previews
@Composable
fun SearchBarPreview() {
    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp),
        hint = stringResource(id = R.string.what_have_you_lost)
    )
}