
package com.example.petcaretracker.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PetDao {
    @Query("SELECT * FROM pets")
    fun getAllPets(): Flow<List<Pet>>

    @Query("SELECT * FROM pets WHERE id = :id")
    suspend fun getPetById(id: Int): Pet?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pet: Pet)

    @Update
    suspend fun update(pet: Pet)

    @Delete
    suspend fun delete(pet: Pet)
}
