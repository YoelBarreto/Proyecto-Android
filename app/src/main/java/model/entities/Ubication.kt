package model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ubications")
data class Ubication(
    @PrimaryKey(autoGenerate = true)
    val ubicationId: Int = 0,
    val x: Double,
    val y: Double,
    val animalId: Int
)