package com.sedsoftware.nxmods.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState
import com.sedsoftware.nxmods.component.modlist.NxModsList

@Composable
fun NxModsListContent(component: NxModsList) {
    val model: NxModsList.Model by component.models.subscribeAsState()
    NxModsListScreen(model)
}

@Composable
internal fun NxModsListScreen(
    model: NxModsList.Model,
    modifier: Modifier = Modifier
) {
    LazyColumn {
        items(model.mods) { item ->
            Text(
                text = item.name,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(all = 8.dp)
            )
        }
    }
}
