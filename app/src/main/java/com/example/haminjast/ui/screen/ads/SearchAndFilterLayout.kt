package com.example.haminjast.ui.screen.ads

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.haminjast.R
import com.example.haminjast.ui.component.ToggleButtonOption
import com.example.haminjast.ui.theme.PrimaryBlack
import com.example.haminjast.ui.theme.VazirFont
import com.example.haminjast.ui.util.RTLPixel5Previews

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchAndFilterLayout(
    modifier: Modifier = Modifier,
    posterFilterOptions: PosterFilterOptions = PosterFilterOptions(),
    text: String = "",
    onValueChange: (String) -> Unit = {},
    isExpanded: Boolean = false,
    onPosterTypeItemSelected: (Int) -> Unit = {},
    onSelectLocationOnMapClicked: () -> Unit = {},
    onSelectStartDateClicked: () -> Unit = {},
    onSelectEndDateClicked: () -> Unit = {},
    onOnlySpecialPosterToggle: () -> Unit = {},
    onCloseFilterOptionsClicked: () -> Unit = {},
    onResetFilterOptionsClicked: () -> Unit = {},
    onSearchClicked: () -> Unit = {},
    onFilterClicked: () -> Unit = {},
) {
    Column(
        modifier = modifier
    ) {
        if (isExpanded) {
            SearchFilterDropdown(
                posterFilterOptions = posterFilterOptions,
                onPosterTypeItemSelected = onPosterTypeItemSelected,
                onSelectLocationOnMapClicked = onSelectLocationOnMapClicked,
                onSelectStartDateClicked = onSelectStartDateClicked,
                onSelectEndDateClicked = onSelectEndDateClicked,
                onOnlySpecialPosterToggle = onOnlySpecialPosterToggle,
                onCloseFilterOptionsClicked = onCloseFilterOptionsClicked,
                onResetFilterOptionsClicked = onResetFilterOptionsClicked,
            )
        }

        SearchBar(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 16.dp)
                .fillMaxWidth()
                .height(44.dp),
            searchPhrase = text,
            onSearchPhraseChanged = { onValueChange(it) },
            onSearchClicked = onSearchClicked,
            onFilterClicked = onFilterClicked,
            hint = stringResource(id = R.string.what_have_you_lost)
        )
    }
}


@Composable
fun SearchFilterDropdown(
    modifier: Modifier = Modifier,
    posterFilterOptions: PosterFilterOptions = PosterFilterOptions(),
    onPosterTypeItemSelected: (Int) -> Unit = {},
    onSelectLocationOnMapClicked: () -> Unit = {},
    onSelectStartDateClicked: () -> Unit = {},
    onSelectEndDateClicked: () -> Unit = {},
    onOnlySpecialPosterToggle: () -> Unit = {},
    onCloseFilterOptionsClicked: () -> Unit = {},
    onResetFilterOptionsClicked: () -> Unit = {},
) {
    Column(modifier = modifier.fillMaxWidth()) {
        SearchAndFilterLayoutTopBar(
            modifier = Modifier
                .padding(
                    bottom = 24.dp,
                    start = 14.dp,
                    end = 20.dp
                )
                .fillMaxWidth(),
            onCloseClicked = onCloseFilterOptionsClicked,
            onResetFilterOptionsClicked = onResetFilterOptionsClicked,
        )
        OneRowSelector(
            modifier = Modifier
                .padding(start = 36.dp, end = 32.dp)
                .fillMaxWidth()
                .height(28.dp),
            selectedItem = posterFilterOptions.posterType.ordinal,
            onItemSelected = onPosterTypeItemSelected
        )
        LocationSelector(
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 20.dp)
                .height(42.dp)
                .fillMaxWidth(),
            text = posterFilterOptions.locationOptions.vicinity,
            onSelectOnMapClicked = onSelectLocationOnMapClicked
        )
        DatePeriodSelector(
            modifier = Modifier
                .padding(start = 36.dp, end = 20.dp)
                .fillMaxWidth(),
            startText = posterFilterOptions.startDate,
            endText = posterFilterOptions.endDate,
            onSelectStartDateClicked = onSelectStartDateClicked,
            onSelectEndDateClicked = onSelectEndDateClicked,
        )
        TitleSwitchToggle(
            modifier = Modifier
                .padding(start = 36.dp, end = 32.dp, top = 16.dp)
                .fillMaxWidth(),
            title = stringResource(id = R.string.only_special_posters),
            isOn = posterFilterOptions.onlySpecialPosters,
            onToggle = onOnlySpecialPosterToggle
        )
    }
}

@Composable
fun SearchAndFilterLayoutTopBar(
    modifier: Modifier = Modifier,
    onCloseClicked: () -> Unit = {},
    onResetFilterOptionsClicked: () -> Unit = {}
) {
    Row(
        modifier = modifier,
        verticalAlignment = CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        IconButton(
            modifier = Modifier
                .size(32.dp), onClick = onCloseClicked
        ) {
            Icon(
                modifier = Modifier.size(20.dp),
                painter = painterResource(id = R.drawable.ic_cross),
                contentDescription = null
            )
        }
        TextButton(
            shape = RoundedCornerShape(8.dp),
            contentPadding = PaddingValues(all = 0.dp),
            onClick = onResetFilterOptionsClicked
        ) {
            Text(
                text = stringResource(R.string.reset),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontFamily = VazirFont,
                    fontWeight = FontWeight(600),
                    color = PrimaryBlack,
                    textAlign = TextAlign.Center,
                )
            )
        }
    }
}

@RTLPixel5Previews
@Composable
fun SearchAndFilterLayoutTopBarPreview() {
    SearchAndFilterLayoutTopBar(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()
    )
}


@RTLPixel5Previews
@Composable
fun SearchFilterDropdownPreview() {
    SearchFilterDropdown()
}


@RTLPixel5Previews
@Composable
fun SearchAndFilterLayoutPreview() {
    SearchAndFilterLayout(
        text = "متن جستجو",
        isExpanded = true,
    )
}