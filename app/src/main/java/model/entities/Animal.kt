package model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "animals",
)
data class Animal(
    @PrimaryKey(autoGenerate = true)
    val animalId: Int = 0,
    val name: String,
    val specie: String,
)