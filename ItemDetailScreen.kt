package com.ferreirg.inventory.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ferreirg.inventory.viewmodel.InventoryViewModel
import kotlinx.coroutines.launch

@Composable
fun ItemDetailScreen(
    itemId: String,
    viewModel: InventoryViewModel,
    onBack: () -> Unit,
    onEdit: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    var item by remember { mutableStateOf<com.ferreirg.inventory.model.Item?>(null) }

    LaunchedEffect(itemId) {
        item = viewModel.getItemById(itemId)
    }

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text("Detalle del ítem") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                },
                actions = {
                    IconButton(onClick = onEdit) {
                        Icon(Icons.Default.Edit, contentDescription = "Editar")
                    }
                }
            )
        }
    ) { padding ->
        item?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                Text(text = "Nombre: ${it.name}", style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Cantidad: ${it.quantity}", style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = "Descripción: ${it.description ?: "N/A"}", style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = {
                        coroutineScope.launch {
                            viewModel.deleteItem(it)
                            onBack()
                        }
                    }
                ) {
                    Text("Eliminar")
                }
            }
        }
    }
}
