package com.example.haminjast.ui.screen.ads

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.haminjast.ui.component.ToggleButton
import com.example.haminjast.ui.component.ToggleButtonOption
import com.example.haminjast.ui.theme.PrimaryBlack
import com.example.haminjast.ui.theme.VazirFont

@Composable
fun SearchBarDropDown(
    modifier: Modifier,
    onSearchClick: () -> Unit = {},
    onClickStatus : (selectedOptions: Array<ToggleButtonOption>) -> Unit = {},
    onClickSortOrder : (selectedOptions: Array<ToggleButtonOption>) -> Unit = {},
    onClickSortBy : (selectedOptions: Array<ToggleButtonOption>) -> Unit = {},
    onClickState : (selectedOptions: Array<ToggleButtonOption>) -> Unit = {},
    onClickSpecialType : (selectedOptions: Array<ToggleButtonOption>) -> Unit = {},
    onClickAward : (Boolean) -> Unit = {},
    status : String = "both",
    sort : String = "desc",
    sortBy : String = "created_at",
    state : String = "all",
    specialType : String = "all",
    onlyWithAward : Boolean = false,
) {
    var award by remember { mutableStateOf(onlyWithAward) }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ToggleButton(options =
        arrayOf(
            ToggleButtonOption(
                text = "همه",
                iconRes = null
            ),
            ToggleButtonOption(
                text = "گم شده",
                iconRes = null
            ),
            ToggleButtonOption(
                text = "پیدا شده",
                iconRes = null
            ),
        ),
            modifier = Modifier
                .padding(bottom = 8.dp),
            onClick = onClickStatus,
            state = remember {
                mutableStateMapOf(
                    if(status == "both") {
                        "همه" to ToggleButtonOption(
                            text = "همه",
                            iconRes = null
                        )
                    } else if (status == "lost") {
                        "گم شده" to ToggleButtonOption(
                            text = "گم شده",
                            iconRes = null
                        )
                    } else {
                        "پیدا شده" to ToggleButtonOption(
                            text = "پیدا شده",
                            iconRes = null
                        )
                    }
                )
            }
        )
        Text(text = "مرتب سازی", style = TextStyle(
            fontSize = 14.sp,
            fontFamily = VazirFont,
            fontWeight = FontWeight(600),
            color = PrimaryBlack.copy(alpha = 0.9f),
            textAlign = TextAlign.Right,
        )
        )
        ToggleButton(options =
        arrayOf(
            ToggleButtonOption(
                text = "نزولی",
                iconRes = null
            ),
            ToggleButtonOption(
                text = "صعودی",
                iconRes = null
            ),
        ),
            modifier = Modifier
                .padding(bottom = 8.dp),
            onClick = onClickSortOrder,
            state = remember {
                mutableStateMapOf(
                    if(sort == "desc") {
                        "نزولی" to ToggleButtonOption(
                            text = "نزولی",
                            iconRes = null
                        )
                    } else {
                        "صعودی" to ToggleButtonOption(
                            text = "صعودی",
                            iconRes = null
                        )
                    }
                )
            }
        )
        Text(text = "مرتب سازی بر اساس", style = TextStyle(
            fontSize = 14.sp,
            fontFamily = VazirFont,
            fontWeight = FontWeight(600),
            color = PrimaryBlack.copy(alpha = 0.9f),
            textAlign = TextAlign.Right,
        )
        )
        ToggleButton(options =
        arrayOf(
            ToggleButtonOption(
                text = "تاریخ ساخت آگهی",
                iconRes = null
            ),
            ToggleButtonOption(
                text = "تاریخ آپدبت آگهی" ,
                iconRes = null
            ),
        ),
            modifier = Modifier
                .padding(bottom = 8.dp),
            onClick = onClickSortBy,
            state = remember {
                mutableStateMapOf(
                    if(sortBy == "created_at") {
                        "تاریخ ساخت آگهی" to ToggleButtonOption(
                            text = "تاریخ ساخت آگهی",
                            iconRes = null
                        )
                    } else {
                        "تاریخ آپدبت آگهی" to ToggleButtonOption(
                            text = "تاریخ آپدبت آگهی",
                            iconRes = null
                        )
                    }
                )
            }
        )
        Text(text = "وضعیت", style = TextStyle(
            fontSize = 14.sp,
            fontFamily = VazirFont,
            fontWeight = FontWeight(600),
            color = PrimaryBlack.copy(alpha = 0.9f),
            textAlign = TextAlign.Right,
        )
        )
        ToggleButton(options =
        arrayOf(
            ToggleButtonOption(
                text = "همه",
                iconRes = null
            ),
            ToggleButtonOption(
                text = "در حال بررسی",
                iconRes = null
            ),
            ToggleButtonOption(
                text = "تایید شده",
                iconRes = null
            ),
            ToggleButtonOption(
                text = "رد شده",
                iconRes = null
            ),
        ),
            modifier = Modifier
                .padding(bottom = 8.dp),
            onClick = onClickState,
            state = remember {
                mutableStateMapOf(
                    if(state == "all") {
                        "همه" to ToggleButtonOption(
                            text = "همه",
                            iconRes = null
                        )
                    } else if (state == "pending") {
                        "در حال بررسی" to ToggleButtonOption(
                            text = "در حال بررسی",
                            iconRes = null
                        )
                    } else if (state == "approved") {
                        "تایید شده" to ToggleButtonOption(
                            text = "تایید شده",
                            iconRes = null
                        )
                    } else {
                        "رد شده" to ToggleButtonOption(
                            text = "رد شده",
                            iconRes = null
                        )
                    }
                )
            }
        )
        Text(text = "نوع آگهی", style = TextStyle(
            fontSize = 14.sp,
            fontFamily = VazirFont,
            fontWeight = FontWeight(600),
            color = PrimaryBlack.copy(alpha = 0.9f),
            textAlign = TextAlign.Right,
        )
        )
        ToggleButton(options =
        arrayOf(
            ToggleButtonOption(
                text = "همه",
                iconRes = null
            ),
            ToggleButtonOption(
                text = "عادی",
                iconRes = null
            ),
            ToggleButtonOption(
                text = "ویژه",
                iconRes = null
            ),
        ),
            modifier = Modifier
                .padding(bottom = 8.dp),
            onClick = onClickSpecialType,
            state = remember {
                mutableStateMapOf(
                    if(specialType == "all") {
                        "همه" to ToggleButtonOption(
                            text = "همه",
                            iconRes = null
                        )
                    } else if (specialType == "normal") {
                        "عادی" to ToggleButtonOption(
                            text = "عادی",
                            iconRes = null
                        )
                    } else {
                        "ویژه" to ToggleButtonOption(
                            text = "ویژه",
                            iconRes = null
                        )
                    }
                )
            }
        )
        Text(text = "فقط آگهی های دارای جایزه", style = TextStyle(
            fontSize = 14.sp,
            fontFamily = VazirFont,
            fontWeight = FontWeight(600),
            color = PrimaryBlack.copy(alpha = 0.9f),
            textAlign = TextAlign.Right,
        )
        )
        Switch(checked = award, onCheckedChange = {
            award = it
            onClickAward(it)
        })
        OutlinedButton(onClick = onSearchClick) {
            Text(text = "جستجو")
        }
    }
}