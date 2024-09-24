package com.example.notes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notes.data.NoteModel
import com.example.notes.data.NoteModelViewModel
import com.example.notes.ui.AddNoteAlertDialog
import com.example.notes.ui.EditNoteAlertDialog

@Composable
fun MainPage(vm: NoteModelViewModel){
    val dataList = vm.getSortedList().collectAsState(initial = emptyList())
    val shouldShowAddDialog = remember { mutableStateOf(false) }
    val shouldShowEditDialog = remember { mutableStateOf(false) }
    val mutItem = remember { mutableStateOf(NoteModel(name = "",text = "")) }
    if (shouldShowAddDialog.value) {
        AddNoteAlertDialog(shouldShowDialog = shouldShowAddDialog){ name, text->
            vm.add(NoteModel(name = name,text = text))
        }
    }
    if(shouldShowEditDialog.value){
        EditNoteAlertDialog(shouldShowEditDialog, mutItem,vm)


    }
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize().padding(12.dp))
        {
            Text(
                text = stringResource(R.string.app_name), fontSize = 30.sp,
                fontWeight = FontWeight.Bold, fontFamily = FontFamily.SansSerif
            )
            Spacer(Modifier.height(8.dp))

            LazyVerticalStaggeredGrid(columns = StaggeredGridCells.Fixed(2)) {
                items(dataList.value) {
                    DrawNotesCard(it,shouldShowEditDialog,mutItem)
                }
            }
        }
        FloatingActionButton(
            onClick = {
                shouldShowAddDialog.value=true
            },
            containerColor = Color(0xFF654265),
            contentColor = Color.White,
            modifier = Modifier.align(Alignment.BottomEnd).padding(30.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add note"
            )
        }
    }
}

@Composable
fun DrawNotesCard(item: NoteModel, shouldShowEditDialog: MutableState<Boolean>,mutItem:MutableState<NoteModel>){
    Card(onClick = {
        shouldShowEditDialog.value=true
        mutItem.value = item
    }, modifier = Modifier.heightIn(max = 200.dp).padding(8.dp)) {
        Column(Modifier.padding(4.dp)) {
            Text(text = item.name, fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text= item.text, fontSize = 16.sp)
        }
    }
}

