package model.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "ubications",
    foreignKeys = [
        ForeignKey(
            entity = Animal::class,
            parentColumns = ["animalId"],
            childColumns = ["animalId"],
            onDelete = ForeignKey.CASCADE // Elimina las ubicaciones relacionadas cuando se borra el animal
        )
    ]
)
data class Ubication(
    @PrimaryKey(autoGenerate = true)
    val ubicationId: Int = 0,
    val x: Double,
    val y: Double,
    val animalId: Int
)