package com.example.haminjast.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.haminjast.ui.screen.common.TagText
import com.example.haminjast.ui.theme.PrimaryBlack
import com.example.haminjast.ui.theme.TagGray
import com.example.haminjast.ui.theme.VazirFont
import com.example.haminjast.ui.util.RTLPixel5Previews

@Composable
fun TagSelector(
    modifier: Modifier = Modifier,
    fieldText: String = "",
    onValueChanged: (String) -> Unit = {},
    suggestedTags:List<String> = listOf(),
    selectedTags:List<String> = listOf(),
    onSuggestedTagClicked: (String) -> Unit = {},
    onSelectedTagClicked: (String) -> Unit = {},
) {

//    val finalText by produceState(
//        initialValue = buildAnnotatedString { },
//        key1 = selectedTags,
//        key2 = text,
//        producer = {
//            value = buildAnnotatedString {
//                selectedTags.forEach {
//                    withStyle(
//                        style = SpanStyle(
//                            background = TagGray,
//                            fontSize = 12.sp,
//                            fontFamily = VazirFont,
//                            fontWeight = FontWeight(400),
//                            color = PrimaryBlack,
//                        )
//                    ) {
//                        append(" $it ")
//                    }
//                    append(" ")
//                }
//                append(text)
//            }
//        }
//    )


    BasicTextField(
        modifier = modifier
            .background(Color.Transparent)
            .heightIn(min = 40.dp)
            .fillMaxWidth()
            .border(
                width = 1.5.dp,
                color = PrimaryBlack.copy(alpha = 0.4f),
                shape = RoundedCornerShape(8.dp)
            ),
        value = fieldText,
        onValueChange = { s: String -> onValueChanged(s) },
        textStyle = TextStyle(
            fontSize = 14.sp,
            fontFamily = VazirFont,
            fontWeight = FontWeight(500),
            color = PrimaryBlack.copy(alpha = 0.9f),
            textAlign = TextAlign.Right,
        ),
        decorationBox = { innerTextField ->
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)) {
                innerTextField()
                ChipList(modifier = Modifier.padding(top = 8.dp, bottom = 24.dp), tags = selectedTags,
                    onChipClicked = {
                        onSelectedTagClicked(it)
                    }
                )
                ChipList(
                    tags = suggestedTags,
                    onChipClicked = {
                        onSuggestedTagClicked(it)
                    }
                )
            }
        }
    )
}

@RTLPixel5Previews
@Composable
fun TagSelectorPreview() {
    TagSelector()
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ChipList(
    modifier: Modifier = Modifier,
    tags: List<String> = fakeTagList,
    onChipClicked: (String) -> Unit = {}
) {
    FlowRow(modifier = modifier.fillMaxWidth()) {
        repeat(tags.size) {
            TagText(
                text = tags[it],
                backgroundColor = TagGray,
                onTagClicked = { onChipClicked(tags[it]) })
        }
    }
}

@RTLPixel5Previews
@Composable
fun ChipListPreview() {
    ChipList()
}

val fakeTagList =
    mutableListOf(
        "دوچرخه",
        "مترو",
        "اتوبوس",
        "کیف",
        "کفش",
        "کلاه",
        "مشکی",
        "سفید",
        "قرمز",
        "کاپشن",
        "کت",
        "کتاب",
        "مداد",
        "خودکار",
        "کیف پول",
        "کلید",
        "موبایل",
    )