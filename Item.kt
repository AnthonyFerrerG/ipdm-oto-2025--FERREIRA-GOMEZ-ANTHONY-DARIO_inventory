package com.ferreirg.inventory.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class Item(
    @PrimaryKey val id: String,
    val name: String,
    val description: String?,
    val quantity: Int
)
