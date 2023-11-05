package com.example.haminjast.ui.screen.createPoster.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.haminjast.R

@Composable
fun AddContactDialog(
    onConfirmClicked: (String, String) -> Unit = { _, _ -> },
    onDismissRequest: () -> Unit
) {
    var name by remember {
        mutableStateOf("")
    }

    var value by remember {
        mutableStateOf("")
    }

    Dialog(
        onDismissRequest = { onDismissRequest() }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(375.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                TextField(value = name, onValueChange = { name = it })
                Spacer(modifier = Modifier.height(16.dp))
                TextField(value = value, onValueChange = { value = it })
                Spacer(modifier = Modifier.height(48.dp))

                Button(onClick = { onConfirmClicked(name, value) }) {
                    Text(text = stringResource(id = R.string.confirm))
                }
            }
        }
    }
}