package com.example.notes.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.notes.R
import com.example.notes.data.NoteModel
import com.example.notes.data.NoteModelViewModel


@Composable
fun EditNoteAlertDialog(
    shouldShowEditDialog: MutableState<Boolean>,
    mutItem: MutableState<NoteModel>,
    vm: NoteModelViewModel
) {
    if (shouldShowEditDialog.value) {
        var name = remember { mutableStateOf(mutItem.value.name) }
        var text = remember { mutableStateOf(mutItem.value.text) }
        AlertDialog(
            onDismissRequest = {
                shouldShowEditDialog.value = false
            },
            title = { Text(text = "Edit this note") },
            text = {
                Column {
                    Row(Modifier.fillMaxWidth().align(Alignment.End), horizontalArrangement = Arrangement.End) {
                        Icon(painter = painterResource(R.drawable.baseline_delete_outline_24),
                            contentDescription = "delete", modifier = Modifier.clickable {
                                vm.delete(mutItem.value)
                                shouldShowEditDialog.value = false
                            })
                    }
                    TextField(
                        value = name.value,
                        onValueChange = { newText ->
                            name.value = newText
                        },
                        placeholder = {
                            Text("Name note")
                        }
                    )
                    Spacer(Modifier.height(8.dp))
                    TextField(
                        value = text.value,
                        onValueChange = { newText ->
                            text.value = newText
                        },
                        placeholder = {
                            Text("Text note")
                        }
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        vm.update(mutItem.value.copy(name=name.value, text = text.value))
                        shouldShowEditDialog.value = false
                    }
                ) {
                    Text(
                        text = "Confirm",
                        color = Color.White
                    )
                }
            }
        )
    }
}

@Composable
fun AddNoteAlertDialog(shouldShowDialog: MutableState<Boolean>, add: (name:String, text:String) -> Unit) {
    if (shouldShowDialog.value) {
        var name = remember { mutableStateOf("") }
        var text = remember { mutableStateOf("") }
        AlertDialog(
            onDismissRequest = {
                shouldShowDialog.value = false
            },
            title = { Text(text = "Add new Notes") },
            text = {
                Column {
                    TextField(
                        value = name.value,
                        onValueChange = { newText ->
                            name.value = newText
                        },
                        placeholder = {
                            Text("Name note")
                        }
                    )
                    Spacer(Modifier.height(8.dp))
                    TextField(
                        value = text.value,
                        onValueChange = { newText ->
                            text.value = newText
                        },
                        placeholder = {
                            Text("Text note")
                        }
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        add(name.value,text.value)
                        shouldShowDialog.value = false
                    }
                ) {
                    Text(
                        text = "Confirm",
                        color = Color.White
                    )
                }
            }
        )
    }
}
