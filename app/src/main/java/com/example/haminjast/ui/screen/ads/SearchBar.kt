package com.example.haminjast.ui.screen.ads

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.haminjast.ui.component.ToggleButtonOption
import com.example.haminjast.ui.theme.PrimaryBlack
import com.example.haminjast.ui.theme.VazirFont
import com.example.haminjast.ui.util.RTLPixel5Previews

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    text: String = "",
    onSearchClick: () -> Unit = {},
    onValueChange: (String) -> Unit = {},
    onClickStatus: (selectedOptions: Array<ToggleButtonOption>) -> Unit = {},
    onClickSortOrder: (selectedOptions: Array<ToggleButtonOption>) -> Unit = {},
    onClickSortBy: (selectedOptions: Array<ToggleButtonOption>) -> Unit = {},
    onClickState: (selectedOptions: Array<ToggleButtonOption>) -> Unit = {},
    onClickSpecialType: (selectedOptions: Array<ToggleButtonOption>) -> Unit = {},
    onClickAward: (Boolean) -> Unit = {},
    isExpanded: Boolean = false,
    onIsExpandedChange: (Boolean) -> Unit = {},
    status: String = "both",
    sort: String = "desc",
    sortBy: String = "created_at",
    state: String = "all",
    specialType: String = "all",
    onlyWithAward: Boolean = false,
    onToggleMapClicked: () -> Unit = {},
) {
    Column(modifier = Modifier.fillMaxWidth().animateContentSize()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            TextField(
                modifier = modifier
                    .heightIn(min = 56.dp)
                    .weight(1f),
                value = text,
                onValueChange = {
                    onValueChange(it)
                },
                leadingIcon = {
                    Icon(
                        imageVector = if (!isExpanded) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
                        contentDescription = null,
                        Modifier.clickable {
                            onIsExpandedChange(!isExpanded)
                        }
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.surface,
                    cursorColor = MaterialTheme.colorScheme.primary
                ),
                placeholder = {
                    Text(
                        text = "جستجو در آگهی ها",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontFamily = VazirFont,
                            fontWeight = FontWeight(400),
                            color = PrimaryBlack.copy(alpha = 0.8f),
                            textAlign = TextAlign.Right,
                        )
                    )
                },
            )
            IconButton(onClick = onToggleMapClicked) {
                Icon(imageVector = Icons.Filled.LocationOn, contentDescription = null)
            }
        }
        if (isExpanded) {
            SearchBarDropDown(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 56.dp),
                onClickStatus = onClickStatus,
                onClickSortOrder = onClickSortOrder,
                onClickSortBy = onClickSortBy,
                onClickState = onClickState,
                onClickSpecialType = onClickSpecialType,
                onClickAward = onClickAward,
                onSearchClick = {
                    onSearchClick()
                    onIsExpandedChange(false)
                },
                status = status,
                sort = sort,
                sortBy = sortBy,
                state = state,
                specialType = specialType,
                onlyWithAward = onlyWithAward,
            )
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp),
            color = PrimaryBlack.copy(alpha = 0.1f)
        )
    }

}

@RTLPixel5Previews
@Composable
fun SearchBarPreview() {
    SearchBar(
        text = "متن جستجو",
        isExpanded = false,
        status = "both",
        sort = "desc",
        sortBy = "created_at",
        state = "all",
        specialType = "all",
        onlyWithAward = false,
    )
}