package com.ferreirg.inventory.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ferreirg.inventory.model.Item
import com.ferreirg.inventory.viewmodel.InventoryViewModel

@Composable
fun ItemListScreen(
    viewModel: InventoryViewModel,
    onAddItem: () -> Unit,
    onItemClick: (String) -> Unit
) {
    val items = viewModel.items.collectAsState()

    Scaffold(
        topBar = {
            SmallTopAppBar(title = { Text("Inventario") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddItem) {
                Text("+")
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            items(items.value) { item ->
                ItemRow(item = item, onClick = { onItemClick(item.id) })
            }
        }
    }
}

@Composable
fun ItemRow(item: Item, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Text(text = item.name, style = MaterialTheme.typography.titleMedium)
        Text(text = "Cantidad: ${item.quantity}", style = MaterialTheme.typography.bodyMedium)
    }
}
