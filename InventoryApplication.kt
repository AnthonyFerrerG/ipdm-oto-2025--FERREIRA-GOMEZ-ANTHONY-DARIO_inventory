package com.ferreirg.inventory

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class InventoryApplication : Application() {
    // No corremos trabajo en segundo plano, pero dejamos preparado el scope si se requiere
    val applicationScope = CoroutineScope(SupervisorJob())

    // Lazy database instance (Room)
    val database by lazy { InventoryDatabase.getDatabase(this) }
    val repository by lazy { InventoryRepository(database.itemDao()) }
}
