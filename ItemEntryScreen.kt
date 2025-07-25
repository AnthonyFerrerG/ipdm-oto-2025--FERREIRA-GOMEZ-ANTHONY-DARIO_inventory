package com.ferreirg.inventory.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.ferreirg.inventory.viewmodel.InventoryViewModel
import kotlinx.coroutines.launch

@Composable
fun ItemEntryScreen(
    itemId: String?,
    viewModel: InventoryViewModel,
    onBack: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    var name by remember { mutableStateOf("") }
    var quantityText by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    LaunchedEffect(itemId) {
        if (itemId != null) {
            isLoading = true
            val item = viewModel.getItemById(itemId)
            if (item != null) {
                name = item.name
                quantityText = item.quantity.toString()
                description = item.description ?: ""
            }
            isLoading = false
        }
    }

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text(if (itemId == null) "Agregar ítem" else "Editar ítem") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                val quantity = quantityText.toIntOrNull() ?: 0
                if (name.isNotBlank() && quantity > 0) {
                    viewModel.addItem(name, description.ifBlank { null }, quantity)
                    onBack()
                }
            }) {
                Text("Guardar")
            }
        }
    ) { padding ->
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Nombre") },
                    singleLine = true
                )
                OutlinedTextField(
                    value = quantityText,
                    onValueChange = { quantityText = it.filter { char -> char.isDigit() } },
                    label = { Text("Cantidad") },
                    keyboardOptions = androidx.compose.ui.text.input.KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Descripción") },
                    singleLine = false,
                    maxLines = 5
                )
            }
        }
    }
}
