package com.ferreirg.inventory.data

import com.ferreirg.inventory.model.Item
import kotlinx.coroutines.flow.Flow

class InventoryRepository(private val itemDao: ItemDao) {

    val items: Flow<List<Item>> = itemDao.getItems()

    suspend fun getItem(id: String): Item? = itemDao.getItemById(id)

    suspend fun insertItem(item: Item) = itemDao.insertItem(item)

    suspend fun deleteItem(item: Item) = itemDao.deleteItem(item)
}
