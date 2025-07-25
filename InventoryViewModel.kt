package com.ferreirg.inventory.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ferreirg.inventory.data.InventoryRepository
import com.ferreirg.inventory.model.Item
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.*

class InventoryViewModel(private val repository: InventoryRepository) : ViewModel() {

    val items: StateFlow<List<Item>> = repository.items
        .map { it.sortedBy { item -> item.name } }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun addItem(name: String, description: String?, quantity: Int) {
        val newItem = Item(
            id = UUID.randomUUID().toString(),
            name = name,
            description = description,
            quantity = quantity
        )
        viewModelScope.launch {
            repository.insertItem(newItem)
        }
    }

    fun deleteItem(item: Item) {
        viewModelScope.launch {
            repository.deleteItem(item)
        }
    }

    suspend fun getItemById(id: String): Item? {
        return repository.getItem(id)
    }
}
