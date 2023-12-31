package com.example.haminjast.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

enum class SelectionType {
    NONE,
    SINGLE,
    MULTIPLE,
}

data class ToggleButtonOption(
    val text: String,
    val iconRes: Int?,
)

@Composable
fun ToggleButton(
    options: Array<ToggleButtonOption>,
    modifier: Modifier = Modifier,
    type: SelectionType = SelectionType.SINGLE,
    onClick: (selectedOptions: Array<ToggleButtonOption>) -> Unit = {},
    state : SnapshotStateMap<String, ToggleButtonOption> = remember { mutableStateMapOf() },
) {
    OutlinedButton(
        onClick = { },
        border = BorderStroke(1.dp, nonHighlighted()),
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = nonHighlighted()),
        contentPadding = PaddingValues(0.dp, 0.dp),
        modifier = modifier
            .padding(0.dp)
            .height(30.dp),
    ) {
        if (options.isEmpty()) {
            return@OutlinedButton
        }
        val onItemClick: (option: ToggleButtonOption) -> Unit = { option ->
            if (type == SelectionType.SINGLE) {
                options.forEach {
                    val key = it.text
                    if (key == option.text) {
                        state[key] = option
                    } else {
                        state.remove(key)
                    }
                }
            } else {
                val key = option.text
                if (!state.contains(key)) {
                    state[key] = option
                } else {
                    state.remove(key)
                }
            }
            onClick(state.values.toTypedArray())
        }
        if (options.size == 1) {
            val option = options.first()
            SelectionPill(
                option = option,
                selected = state.contains(option.text),
                onClick = onItemClick,
            )
            return@OutlinedButton
        }
        val first = options.first()
        val last = options.last()
        val middle = options.slice(1..options.size - 2)
        SelectionPill(
            option = first,
            selected = state.contains(first.text),
            onClick = onItemClick,
        )
        VerticalDivider()
        middle.map { option ->
            SelectionPill(
                option = option,
                selected = state.contains(option.text),
                onClick = onItemClick,
            )
            VerticalDivider()
        }
        SelectionPill(
            option = last,
            selected = state.contains(last.text),
            onClick = onItemClick,
        )
    }
}

fun nonHighlighted(): Color {
    return Color(0xFF8D8D8D)
}

fun highlighted(): Color {
    return Color(0xFF2196F3)
}

@Composable
fun VerticalDivider() {
    Divider(
        modifier = Modifier
            .fillMaxHeight()
            .width(2.dp)
    )
}


@Composable
fun SelectionPill(
    option: ToggleButtonOption,
    selected: Boolean,
    onClick: (option: ToggleButtonOption) -> Unit = {}
) {
    Button(
        onClick = { onClick(option) },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.background,
        ),
        shape = RoundedCornerShape(0),
        elevation = null,
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier.padding(14.dp, 0.dp),
    ) {
        Row(
            modifier = Modifier.padding(0.dp),
            verticalAlignment = Alignment.Bottom,
        ) {
            Text(
                text = option.text,
                color = if (selected) MaterialTheme.colorScheme.primary else nonHighlighted(),
                modifier = Modifier.padding(0.dp),
            )
            if (option.iconRes != null) {
                Icon(
                    painterResource(id = option.iconRes),
                    contentDescription = null,
                    tint = if (selected) MaterialTheme.colorScheme.primary else nonHighlighted(),
                    modifier = Modifier.padding(4.dp, 2.dp, 2.dp, 2.dp),
                )
            }
        }
    }
}